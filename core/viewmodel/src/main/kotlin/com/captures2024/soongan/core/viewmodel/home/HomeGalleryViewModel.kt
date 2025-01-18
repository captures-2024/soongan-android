package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetGalleryUseCase
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.PostOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomeGalleryViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getGalleryUseCase: GetGalleryUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<HomeGalleryViewModel.State, HomeGalleryViewModel.Effect, HomeGalleryViewModel.Intent>(savedStateHandle) {

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
            val post: UserPost.PhotoPost,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object RefreshGallery : Intent

        data object LoadNextPage : Intent

        data class OnClickPost(
            val post: GalleryPostDto
        ) : Intent

        data object OnClickFilter : Intent

        data class OnClickSortFilter(
            val postOrderType: PostOrderType
        ) : Intent

        data object OnBottomModalDismissRequest : Intent
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

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> fetchPostPage(page = 0)

            is Intent.RefreshGallery -> fetchPostPage(page = 0, isRefreshing = true)

            is Intent.LoadNextPage -> fetchPostPage(page = currentState.nextPage)

            is Intent.OnBottomModalDismissRequest -> onBottomModalDismissRequest()

            is Intent.OnClickFilter -> onClickFilter()

            is Intent.OnClickPost -> onClickPost(intent)

            is Intent.OnClickSortFilter -> onClickSortFilter(intent)
        }
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

    private suspend fun fetchPostPage(page: Int = 0, isRefreshing: Boolean = false) = launch {
        if (currentState.paginationStatus in listOf(
                PaginationStatus.LOADING,
                PaginationStatus.PAGINATING,
            )
        ) return@launch

        val isInitPage = (page == 0)

        setUpLoading(isInitPage = isInitPage, isRefreshing = isRefreshing)

        delay(2_000)

        getGalleryUseCase(
            params = GetGalleryUseCase.Params(
                round = null,
                orderType = currentState.postOrderType.name,
                page = page,
                pageSize = PAGE_SIZE,
            )
        ).onSuccess { galleryDto ->
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
        }.onFailure {
            reduce {
                copy(
                    isRefreshing = false,
                    paginationStatus = PaginationStatus.ERROR
                )
            }
        }

        analyticsHelper.d(
            logVariable = arrayOf(
                LogElementArgument("pagingStatus", "${currentState.paginationStatus}"),
                LogElementArgument("posts", "${currentState.posts}"),
                LogElementArgument("page", "${currentState.nextPage}"),
                LogElementArgument("haspage", "${currentState.hasNextPage}"),
            ),
            message = "fetch post Page"
        )
    }

    private fun onBottomModalDismissRequest() {
        reduce {
            copy(
                isShowBottomSheet = false
            )
        }
    }

    private fun onClickFilter() {
        reduce {
            copy(
                isShowBottomSheet = true
            )
        }
    }

    private suspend fun onClickSortFilter(intent: Intent.OnClickSortFilter) = launch {
        reduce {
            copy(
                isShowBottomSheet = false,
                postOrderType = intent.postOrderType
            )
        }

        fetchPostPage(page = 0)
    }

    private fun onClickPost(intent: Intent.OnClickPost) {
//        postSideEffect(HomeGallerySideEffect.NavigateToHomePost(intent.post))
    }

    companion object {
        private const val TAG = "HomeGalleryVM"

        private const val PAGE_SIZE = 20
    }
}