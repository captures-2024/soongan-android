package com.captures2024.soongan.core.viewmodel.feed

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
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.PostOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
    private val getFilteredGalleryByReportTargetIdsUseCase: GetFilteredGalleryByReportTargetIdsUseCase,
) : NewBaseViewModel<FeedViewModel.State, FeedViewModel.Effect, FeedViewModel.Intent>(
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
        val isOpenDropDown: Boolean = false,
        val isOpenFilterBottomSheet: Boolean = false,
        val postOrderType: PostOrderType = PostOrderType.MOST_LIKED,
        val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
        val titleOptions: List<Pair<Int, String>> = emptyList(), /* string value : "${round}회차 | title" */
        val currentRound: Int = 1,
        internal val nextPage: Int = 0,
        internal val hasNextPage: Boolean = false,
        internal val feed: Map<Int, List<GalleryPostDto>> = emptyMap(),
    ) : UIState {

        val currentTitleOption: Pair<Int, String>
            get() = titleOptions[currentRound - 1]

        val isFirstPage: Boolean
            get() = (nextPage == 0)

        val currentRoundGallery: List<GalleryPostDto>
            get() = feed[currentRound] ?: emptyList()

        override fun toString(): String {
            return "State(isLoading=$isLoading, isRefreshing = $isRefreshing, isShowDropDown = $isOpenDropDown, isShowBottomSheet=$isOpenFilterBottomSheet, titleItems:$titleOptions, postOrderType:$postOrderType, currentRound:$currentRound, titleOptions:$titleOptions, feeds:$feed, currentTitleOption:$currentTitleOption, currentRoundGallery:$currentRoundGallery)"
        }
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToHomePost(
            val postId: Long,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object RefreshFeed : Intent

        data class OnClickRound(
            val newRound: Int,
        ) : Intent

        data object OnClickFilter : Intent

        data object OnFilterDismissRequest : Intent

        data class OnClickSortFilter(
            val postOrderType: PostOrderType,
        ) : Intent

        data object LoadNextPage : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

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

            is Intent.RefreshFeed -> launch { handleRefreshFeed() }

            is Intent.OnClickRound -> launch { handleOnClickRound(intent) }

            is Intent.OnClickFilter -> handleOnClickFilter()

            is Intent.OnFilterDismissRequest -> handleOnFilterDismissRequest()

            is Intent.OnClickSortFilter -> launch { handleOnClickSortFilter(intent) }

            is Intent.LoadNextPage -> launch { handleLoadNextPage() }

            is Intent.OnClickPost -> handleOnClickPost(intent)

            is Intent.HidePost -> handleOnReportedPost(intent)
        }
    }

    private suspend fun handleInit() {
        syncFeedInfo()
    }

    private suspend fun handleRefreshFeed() {
        reduce {
            copy(
                isRefreshing = true,
                nextPage = 0,
            )
        }

        fetchFeedPage()

        reduce {
            copy(
                isRefreshing = false,
            )
        }
    }

    private suspend fun handleOnClickRound(intent: Intent.OnClickRound) {
        reduce {
            copy(
                currentRound = intent.newRound,
                nextPage = 0,
            )
        }

        fetchFeedPage()
    }

    private fun handleOnClickFilter() {
        reduce {
            copy(
                isOpenFilterBottomSheet = true,
            )
        }
    }

    private fun handleOnFilterDismissRequest() {
        reduce {
            copy(
                isOpenFilterBottomSheet = false,
            )
        }
    }

    private suspend fun handleOnClickSortFilter(intent: Intent.OnClickSortFilter) {
        reduce {
            copy(
                isOpenFilterBottomSheet = false,
                postOrderType = intent.postOrderType,
            )
        }

        fetchFeedPage()
    }

    private suspend fun handleLoadNextPage() {
        reduce {
            copy(
                nextPage = nextPage + 1,
            )
        }

        fetchFeedPage()
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private fun handleOnReportedPost(intent: Intent.HidePost) {
        val targetId = intent.targetId
        val round = currentState.currentRound

        val tempPosts = currentState.feed[round]?.toMutableList() ?: mutableListOf()

        tempPosts.removeAll { it.postId == targetId }

        reduce {
            copy(feed = feed.toMutableMap().apply { put(round, tempPosts) }.toMap())
        }

        analyticsHelper.d { "reported post, postId : $targetId" }
    }

    private suspend fun syncFeedInfo() {
        getTitleOptions()
        fetchFeedPage()
    }

    private suspend fun getTitleOptions() {
        val weeklyContestInfoListDto = getWeeklyContestInfoListUseCase().getOrNull()

        if (weeklyContestInfoListDto == null) {
            analyticsHelper.d { "getTitleOptions - weeklyContestInfoListDto is null" }

            return
        }

        val titleOptions: List<Pair<Int, String>> =
            weeklyContestInfoListDto.weeklyContestInfoList.map { it.round to it.subject }

        reduce {
            copy(
                titleOptions = titleOptions,
            )
        }
    }

    private fun setUpPaginationStatus() {
        if (currentState.isFirstPage) {
            reduce {
                copy(
                    paginationStatus = PaginationStatus.LOADING,
                    feed = emptyMap(),
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

    private suspend fun fetchFeedPage() {
        when (currentState.paginationStatus) {
            PaginationStatus.LOADING,
            PaginationStatus.PAGINATING,
            -> return

            else -> Unit
        }

        setUpPaginationStatus()

        val galleryDto = getFilteredGalleryByReportTargetIdsUseCase(
            params = GetFilteredGalleryByReportTargetIdsUseCase.Params(
                round = currentState.currentRound,
                orderType = currentState.postOrderType.name,
                page = currentState.nextPage,
                pageSize = AppConst.Gallery.PAGE_SIZE,
            ),
        ).getOrNull()

        if (galleryDto == null) {
            analyticsHelper.d { "fetchFeedPage - galleryDto is null" }

            reduce {
                copy(
                    paginationStatus = PaginationStatus.ERROR,
                )
            }

            return
        }

        val round = galleryDto.round ?: currentState.currentRound

        val currentPosts = currentState.feed[round].orEmpty()
        val updatedPosts = currentPosts + galleryDto.posts

        val updatedFeed = currentState.feed.toMutableMap().apply {
            put(round, updatedPosts)
        }.toMap()

        reduce {
            copy(
                paginationStatus = when {
                    !galleryDto.hasNext -> PaginationStatus.EXHAUST

                    galleryDto.posts.isEmpty() -> PaginationStatus.EMPTY

                    else -> PaginationStatus.INACTIVE
                },
                hasNextPage = galleryDto.hasNext,
                feed = updatedFeed,
            )
        }
    }
}
