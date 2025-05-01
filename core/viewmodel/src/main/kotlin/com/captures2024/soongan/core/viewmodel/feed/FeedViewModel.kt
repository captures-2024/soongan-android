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
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.mock.mockFeedTitleOptions
import com.captures2024.soongan.core.model.mock.mockPosts
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.PostOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
        val isShowDropDown: Boolean = false, /* cmp: FeedDropDown */
        val isShowBottomSheet: Boolean = false, /* cmp: FeedFilterBottomSheet  */
        val postOrderType: PostOrderType = PostOrderType.MOST_LIKED,
        val currentRound: Int = 1,
        val titleOptions: List<Pair<Int, String>> = emptyList(), /* string value : "${round}회차 | title" */
        internal val feed: Map<Int, List<GalleryPostDto>> = emptyMap(),
    ) : UIState {

        val currentTitleOption: Pair<Int, String>
            get() = titleOptions[currentRound - 1]

        val currentRoundGallery: List<GalleryPostDto>
            get() = feed[currentRound] ?: emptyList()

        override fun toString(): String {
            return "State(isLoading=$isLoading, isRefreshing = $isRefreshing, isShowDropDown = $isShowDropDown, isShowBottomSheet=$isShowBottomSheet, titleItems:$titleOptions, postOrderType:$postOrderType, feeds:$feed)"
        }
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToHomePost(
            val postId: Long,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data class RefreshFeed(
            val round: Int,
        ) : Intent

        data class OnClickRound(
            val newRound: Int,
        ) : Intent

        data object OnClickFilter : Intent

        data object OnFilterDismissRequest : Intent

        data class OnClickSortFilter(
            val round: Int,
            val postOrderType: PostOrderType,
        ) : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

        data class HidePost(
            val round: Int,
            val postId: Long,
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

            is Intent.RefreshFeed -> launch { handleRefreshFeed(intent) }

            is Intent.OnClickRound -> launch { handleOnClickRound(intent) }

            is Intent.OnClickFilter -> handleOnClickFilter()

            is Intent.OnFilterDismissRequest -> handleOnFilterDismissRequest()

            is Intent.OnClickSortFilter -> launch { handleOnClickSortFilter(intent) }

            is Intent.OnClickPost -> handleOnClickPost(intent)

            is Intent.HidePost -> handleOnReportedPost(intent)
        }
    }

    private suspend fun handleInit() {
        getTitleOptions()
        fetchFeedPage()
    }

    private suspend fun handleRefreshFeed(intent: Intent.RefreshFeed) {
        reduce {
            copy(
                isRefreshing = true,
            )
        }

        val round = intent.round
        val orderType = currentState.postOrderType

        fetchFeedPage(round = round, orderType = orderType)
    }

    private suspend fun handleOnClickRound(intent: Intent.OnClickRound) {
        val round = intent.newRound

        fetchFeedPage(round = round, orderType = currentState.postOrderType)
    }

    private fun handleOnClickFilter() {
        reduce {
            copy(
                isShowBottomSheet = true,
            )
        }
    }

    private fun handleOnFilterDismissRequest() {
        reduce {
            copy(
                isShowBottomSheet = false,
            )
        }
    }

    private suspend fun handleOnClickSortFilter(intent: Intent.OnClickSortFilter) {
        val round = intent.round
        val orderType = intent.postOrderType

        reduce {
            copy(
                isShowBottomSheet = false,
                postOrderType = intent.postOrderType,
            )
        }

        fetchFeedPage(round = round, orderType = orderType)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private fun handleOnReportedPost(intent: Intent.HidePost) {
        val round = intent.round
        val postId = intent.postId

        val tempPosts = currentState.feed[round]?.toMutableList() ?: mutableListOf()

        tempPosts.removeAll { it.postId == intent.postId }

        reduce {
            copy(feed = feed.toMutableMap().apply { put(round, tempPosts) }.toMap())
        }

        analyticsHelper.d { "reported post, postId : $postId" }
    }

    private fun getTitleOptions() {
        val mockTitleOptions = mockFeedTitleOptions

        reduce {
            copy(
                titleOptions = mockTitleOptions,
            )
        }
    }

    private suspend fun fetchFeedPage(
        round: Int = 1,
        orderType: PostOrderType = PostOrderType.MOST_LIKED,
    ) {
        val mockFeed = buildMap { put(round, mockPosts) }
        // val feed = getFeedUseCase(round, orderType, + page | cursor)

        delay(1000) // 임시 로딩용

        analyticsHelper.d { "fetchFeedPage - round: $round, orderType: $orderType, feed: $mockFeed" }

        reduce {
            copy(
//                isLoading | paginationStatus.Loading = false
                isRefreshing = false,
                currentRound = round,
                postOrderType = orderType,
                feed = mockFeed,
            )
        }
    }
}
