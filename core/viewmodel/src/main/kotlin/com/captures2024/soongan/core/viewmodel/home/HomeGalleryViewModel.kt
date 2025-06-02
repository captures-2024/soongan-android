package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetFilteredGalleryByReportTargetIdsUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.PostOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeGalleryViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getFilteredGalleryByReportTargetIdsUseCase: GetFilteredGalleryByReportTargetIdsUseCase,
) : NewBaseViewModel<HomeGalleryViewModel.State, HomeGalleryViewModel.Effect, HomeGalleryViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val isOpenFilterBottomSheet: Boolean = false,
        val postOrderType: PostOrderType = PostOrderType.MOST_LIKED,
        val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
        val posts: List<GalleryPostDto> = emptyList(),
        internal val nextPage: Int = 0,
        internal val hasNextPage: Boolean = false,
    ) : UIState {

        val isFirstPage: Boolean
            get() = (nextPage == 0)

        override fun toString(): String {
            return "State(isLoading=$isLoading, isShowBottomSheet=$isOpenFilterBottomSheet, isRefreshing=$isRefreshing, postOrderType=$postOrderType, paginationStatus=$paginationStatus, posts=$posts, nextPage=$nextPage, hasNextPage=$hasNextPage)"
        }
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToHomePost(
            val postId: Long,
        ) : Effect

        data object NavigateToRegistrationPost : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object RefreshGallery : Intent

        data object OnClickFilter : Intent

        data object OnFilterDismissRequest : Intent

        data class OnClickSortFilter(
            val postOrderType: PostOrderType,
        ) : Intent

        data object LoadNextPage : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

        data object OnClickRegistrationText : Intent

        data class HidePost(
            val targetId: Long,
        ) : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }

            is Intent.RefreshGallery -> launch { handleRefreshGallery() }

            is Intent.OnClickFilter -> handleOnClickFilter()

            is Intent.OnFilterDismissRequest -> handleOnFilterDismissRequest()

            is Intent.OnClickSortFilter -> loadingLaunch { handleOnClickSortFilter(intent) }

            is Intent.LoadNextPage -> launch { handleLoadNextPage() }

            is Intent.OnClickPost -> handleOnClickPost(intent)

            is Intent.OnClickRegistrationText -> handleOnClickRegistrationText()

            is Intent.HidePost -> handleHidePost(intent)
        }
    }

    private suspend fun handleInit() {
        fetchPostPage()
    }

    private suspend fun handleRefreshGallery() {
        reduce {
            copy(
                isRefreshing = true,
                nextPage = 0,
            )
        }

        fetchPostPage()

        reduce {
            copy(
                isRefreshing = false,
            )
        }
    }

    private suspend fun handleLoadNextPage() {
        reduce {
            copy(
                nextPage = currentState.nextPage + 1,
            )
        }

        fetchPostPage()
    }

    private fun handleOnFilterDismissRequest() {
        reduce {
            copy(
                isOpenFilterBottomSheet = false,
            )
        }
    }

    private fun handleOnClickFilter() {
        reduce {
            copy(
                isOpenFilterBottomSheet = true,
            )
        }
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private suspend fun handleOnClickSortFilter(intent: Intent.OnClickSortFilter) {
        reduce {
            copy(
                isOpenFilterBottomSheet = false,
                postOrderType = intent.postOrderType,
                nextPage = 0,
            )
        }

        fetchPostPage()
    }

    private fun setUpPaginationStatus() {
        if (currentState.isFirstPage) {
            reduce {
                copy(
                    paginationStatus = PaginationStatus.LOADING,
                    posts = emptyList(),
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

    private suspend fun fetchPostPage() {
        when (currentState.paginationStatus) {
            PaginationStatus.LOADING,
            PaginationStatus.PAGINATING,
            -> return

            else -> Unit
        }

        setUpPaginationStatus()

        val galleryDto = getFilteredGalleryByReportTargetIdsUseCase(
            params = GetFilteredGalleryByReportTargetIdsUseCase.Params(
                round = null,
                orderType = currentState.postOrderType.name,
                page = currentState.nextPage,
                pageSize = AppConst.Main.Gallery.PAGE_SIZE,
            ),
        ).getOrNull()

        if (galleryDto == null) {
            analyticsHelper.d { "fetchPostPage - galleryDto is null" }

            reduce {
                copy(
                    paginationStatus = PaginationStatus.ERROR,
                )
            }

            return
        }

        reduce {
            copy(
                paginationStatus = when {
                    !galleryDto.hasNext -> PaginationStatus.EXHAUST

                    galleryDto.posts.isEmpty() -> PaginationStatus.EMPTY

                    else -> PaginationStatus.INACTIVE
                },
                posts = posts + galleryDto.posts,
                hasNextPage = galleryDto.hasNext,
            )
        }
    }

    private fun handleOnClickRegistrationText() {
        postSideEffect(Effect.NavigateToRegistrationPost)
    }

    private fun handleHidePost(intent: Intent.HidePost) {
        val tempPosts = currentState.posts.toMutableList()

        tempPosts.removeAll { it.postId == intent.targetId }

        reduce {
            copy(posts = tempPosts.toList())
        }

        analyticsHelper.d { "reported post, postId : ${intent.targetId}" }
    }
}
