package com.captures2024.soongan.presentation.viewmodel.main.feed

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetFilteredGalleryByReportTargetIdsUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.PaginationStatus
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

typealias TitleOption = Pair<Int, String> // round, subject
typealias Feed = Map<Int, List<GalleryPostDto>> // round, gallery

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
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
    private val getFilteredGalleryByReportTargetIdsUseCase: GetFilteredGalleryByReportTargetIdsUseCase,
) : BaseViewModel<FeedViewModel.State, FeedViewModel.Effect, FeedViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val feedState: FeedState,
        val isOpenTitlePickerBottomSheet: Boolean,
        val isOpenFilterBottomSheet: Boolean,
    ) : UIState {

        data class FeedState(
            val isRefreshing: Boolean,
            val postOrderType: PostOrderType,
            val paginationStatus: PaginationStatus,
            val titleOptions: List<TitleOption>,
            val hasNextPage: Boolean,
            internal val currentRound: Int,
            internal val loadPage: Int,
            internal val feed: Feed,
        ) {
            val isInitPage: Boolean
                get() = (loadPage == 0)

            val currentTitleOption: TitleOption
                get() = titleOptions.getOrNull(currentRound - 1) ?: (currentRound to "")

            val currentRoundGallery: List<GalleryPostDto>
                get() = feed[currentRound] ?: emptyList()
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

        data object OnClickTitle : Intent

        data object OnTitlePickerDismissRequest : Intent

        data class OnSelectTitle(
            val round: Int,
        ) : Intent

        data object OnClickFilter : Intent

        data object OnFilterDismissRequest : Intent

        data class OnClickFilterItem(
            val postOrderType: PostOrderType,
        ) : Intent

        data object LoadNextPage : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

        data class HidePost(
            val postId: Long,
        ) : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            feedState = State.FeedState(
                isRefreshing = false,
                postOrderType = PostOrderType.MOST_LIKED,
                paginationStatus = PaginationStatus.DEFAULT,
                titleOptions = emptyList(),
                currentRound = 1,
                loadPage = 0,
                hasNextPage = false,
                feed = emptyMap(),
            ),
            isOpenTitlePickerBottomSheet = false,
            isOpenFilterBottomSheet = false,
        )
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }

            is Intent.RefreshFeed -> launch { handleRefreshFeed() }

            is Intent.OnClickTitle -> launch { handleOnClickTitle() }

            is Intent.OnTitlePickerDismissRequest -> launch { handleOnTitlePickerDismissRequest() }

            is Intent.OnSelectTitle -> launch { handleOnSelectTitle(intent) }

            is Intent.OnClickFilter -> handleOnClickFilter()

            is Intent.OnFilterDismissRequest -> handleOnFilterDismissRequest()

            is Intent.OnClickFilterItem -> launch { handleOnClickSortFilter(intent) }

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
                feedState = feedState.copy(
                    isRefreshing = true,
                    loadPage = 0,
                )
            )
        }

        val paginationStatus = getRemoteGalleryPage()

        reduce {
            copy(
                feedState = feedState.copy(
                    isRefreshing = false,
                    paginationStatus = paginationStatus,
                )
            )
        }
    }

    private fun handleOnClickTitle() {
        reduce {
            copy(
                isOpenTitlePickerBottomSheet = true,
            )
        }
    }

    private fun handleOnTitlePickerDismissRequest() {
        reduce {
            copy(
                isOpenTitlePickerBottomSheet = false,
            )
        }
    }

    private suspend fun handleOnSelectTitle(intent: Intent.OnSelectTitle) {
        val round = intent.round

        if (round != currentState.feedState.currentRound) {
            val paginationStatus = getRemoteGalleryPage(round = round)

            reduce {
                copy(
                    feedState = feedState.copy(
                        paginationStatus = paginationStatus,
                    )
                )
            }
        }

        handleOnTitlePickerDismissRequest()
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

    private suspend fun handleOnClickSortFilter(intent: Intent.OnClickFilterItem) {
        val postOrderType = intent.postOrderType

        handleOnFilterDismissRequest()

        if (postOrderType != currentState.feedState.postOrderType) {
            val paginationStatus = getRemoteGalleryPage(postOrderType = intent.postOrderType)

            reduce {
                copy(
                    feedState = feedState.copy(
                        paginationStatus = paginationStatus,
                    )
                )
            }
        }
    }

    private suspend fun handleLoadNextPage() {
        reduce {
            copy(
                feedState = feedState.copy(
                    loadPage = feedState.loadPage + 1,
                ),
            )
        }

        val paginationStatus = getRemoteGalleryPage()

        reduce {
            copy(
                feedState = feedState.copy(
                    paginationStatus = paginationStatus,
                )
            )
        }
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private fun handleOnReportedPost(intent: Intent.HidePost) {
        val currentRound = currentState.feedState.currentRound
        val postId = intent.postId

        reduce {
            copy(
                feedState = feedState.copy(
                    feed = feedState.feed.mapValues { (round, gallery) ->
                        if (round == currentRound) {
                            gallery.filter { it.postId != postId }
                        } else gallery
                    }
                )
            )
        }
    }

    private suspend fun syncFeedInfo() {
        getTitleOptions()

        val paginationStatus = getRemoteGalleryPage()

        reduce {
            copy(
                feedState = feedState.copy(
                    paginationStatus = paginationStatus,
                )
            )
        }
    }

    private suspend fun getTitleOptions() {
        val weeklyContestInfoListDto = getWeeklyContestInfoListUseCase().getOrNull()

        if (weeklyContestInfoListDto == null) {
            postSingleButtonDialogUseCase(type = CommonDialogType.NETWORK_ERROR)

            return
        }

        val titleOptions: List<Pair<Int, String>> =
            weeklyContestInfoListDto.weeklyContestInfoList.map { it.round to it.subject }

        reduce {
            copy(
                feedState = feedState.copy(
                    titleOptions = titleOptions,
                )
            )
        }
    }

    private suspend fun getRemoteGalleryPage(
        round: Int = currentState.feedState.currentRound,
        postOrderType: PostOrderType = currentState.feedState.postOrderType,
    ): PaginationStatus {
        val state = currentState.feedState

        when (state.paginationStatus) {
            PaginationStatus.REFRESH_LOAD,
            PaginationStatus.PAGING_LOAD,
            -> return state.paginationStatus

            else -> Unit
        }

        reduce {
            copy(
                feedState = feedState.copy(
                    paginationStatus = when (state.isRefreshing) {
                        true -> PaginationStatus.REFRESH_LOAD
                        false -> PaginationStatus.PAGING_LOAD
                    },
                )
            )
        }

        val galleryDto = getFilteredGalleryByReportTargetIdsUseCase(
            params = GetFilteredGalleryByReportTargetIdsUseCase.Params(
                round = round,
                orderType = postOrderType.name,
                page = state.loadPage,
                pageSize = AppConst.Main.Gallery.PAGE_SIZE,
            ),
        ).getOrNull() ?: return PaginationStatus.FAILED

        val updatedPosts = state.feed[round].orEmpty() + galleryDto.posts

        reduce {
            copy(
                feedState = feedState.copy(
                    currentRound = galleryDto.round ?: 1,
                    hasNextPage = galleryDto.hasNext,
                    feed = feedState.feed + (round to updatedPosts),
                )
            )
        }

        return PaginationStatus.SUCCESS
    }
}
