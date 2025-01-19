package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetGalleryUseCase
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.PostOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomeGalleryViewModel
@Inject
constructor(
    private val getGalleryUseCase: GetGalleryUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<HomeGalleryViewModel.State, HomeGalleryViewModel.Effect, HomeGalleryViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isLoading: Boolean = false,
        val isShowBottomSheet: Boolean = false,
        val isRefreshing: Boolean = false,
        val postOrderType: PostOrderType = PostOrderType.MOST_LIKED,
        val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
        val posts: List<GalleryPostDto> = emptyList(),
        val nextPage: Int = 0,
        val hasNextPage: Boolean = false,
    ) : UIState {
        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("isShowBottomSheet", isShowBottomSheet.toString()),
            LogElementArgument("postOrderType", postOrderType.toString()),
            LogElementArgument("paginationStatus", paginationStatus.toString()),
            LogElementArgument("posts", posts.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToHomePost(
            val postId: Int,
        ) : Effect

        data object NavigateToRegistrationPost : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object RefreshGallery : Intent

        data object LoadNextPage : Intent

        data class OnClickPost(
            val postId: Int,
        ) : Intent

        data object OnClickFilter : Intent

        data class OnClickSortFilter(
            val postOrderType: PostOrderType,
        ) : Intent

        data object OnBottomModalDismissRequest : Intent

        data object OnClickRegistrationText : Intent
    }


    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }

            is Intent.RefreshGallery -> launch { handleRefreshGallery() }

            is Intent.LoadNextPage -> launch { handleLoadNextPage() }

            is Intent.OnBottomModalDismissRequest -> handleOnBottomModalDismissRequest()

            is Intent.OnClickFilter -> handleOnClickFilter()

            is Intent.OnClickPost -> handleOnClickPost(intent)

            is Intent.OnClickSortFilter -> loadingLaunch { handleOnClickSortFilter(intent) }

            is Intent.OnClickRegistrationText -> handleOnClickRegistrationText()
        }
    }

    private suspend fun handleInit() {
        fetchPostPage(page = 0)
    }

    private suspend fun handleRefreshGallery() {
        fetchPostPage(
            page = 0,
            isRefreshing = true,
        )
    }

    private suspend fun handleLoadNextPage() {
        fetchPostPage(page = currentState.nextPage)
    }

    private fun handleOnBottomModalDismissRequest() {
        reduce {
            copy(
                isShowBottomSheet = false
            )
        }
    }

    private fun handleOnClickFilter() {
        reduce {
            copy(
                isShowBottomSheet = true
            )
        }
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private suspend fun handleOnClickSortFilter(intent: Intent.OnClickSortFilter) {
        reduce {
            copy(
                isShowBottomSheet = false,
                postOrderType = intent.postOrderType
            )
        }

        fetchPostPage(page = 0)
    }

    private fun setUpLoading(
        isInitPage: Boolean,
        isRefreshing: Boolean,
    ) {
        if (isInitPage) {
            reduce {
                copy(
                    isRefreshing = isRefreshing,
                    paginationStatus = PaginationStatus.LOADING,
                    posts = emptyList()
                )
            }
        } else {
            reduce {
                copy(
                    paginationStatus = PaginationStatus.PAGINATING,
                )
            }
        }
    }

    private suspend fun fetchPostPage(
        page: Int = 0,
        isRefreshing: Boolean = false,
    ) {
        when (currentState.paginationStatus) {
            PaginationStatus.LOADING,
            PaginationStatus.PAGINATING -> return

            else -> Unit
        }

        val isInitPage = (page == 0)

        setUpLoading(isInitPage = isInitPage, isRefreshing = isRefreshing)

        delay(2_000)

        val galleryDto = getGalleryUseCase(
            params = GetGalleryUseCase.Params(
                round = null,
                orderType = currentState.postOrderType.name,
                page = page,
                pageSize = PAGE_SIZE,
            )
        ).getOrNull()


        if (galleryDto == null) {
            analyticsHelper.d(message = "galleryDto is null")

            reduce {
                copy(
                    isRefreshing = false,
                    paginationStatus = PaginationStatus.ERROR
                )
            }

            return
        }

        analyticsHelper.d(message = "galleryDto is ${galleryDto.posts}")

        reduce {
            copy(
                isRefreshing = false,
                paginationStatus = when {
                    !galleryDto.hasNext -> PaginationStatus.EXHAUST

                    galleryDto.posts.isEmpty() -> PaginationStatus.EMPTY

                    else -> PaginationStatus.INACTIVE
                },
                posts = posts + galleryDto.posts,
                nextPage = page + 1,
                hasNextPage = galleryDto.hasNext
            )
        }
    }

    private fun handleOnClickRegistrationText() {
        postSideEffect(Effect.NavigateToRegistrationPost)
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}