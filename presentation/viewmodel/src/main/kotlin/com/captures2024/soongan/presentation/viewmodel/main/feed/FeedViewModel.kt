package com.captures2024.soongan.presentation.viewmodel.main.feed

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.domain.usecase.contest.GetFilteredGalleryByReportTargetIdsUseCase
import com.captures2024.soongan.domain.usecase.contest.GetHidePostEventUseCase
import com.captures2024.soongan.domain.usecase.contest.GetRegisterPostEventUseCase
import com.captures2024.soongan.domain.usecase.contest.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.PaginationStatus
import com.captures2024.soongan.presentation.viewmodel.model.PostOrderType
import com.captures2024.soongan.presentation.viewmodel.model.TitleOption
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
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
    private val getFilteredGalleryByReportTargetIdsUseCase: GetFilteredGalleryByReportTargetIdsUseCase,
    private val getRegisterPostEventUseCase: GetRegisterPostEventUseCase,
    private val getHidePostEventUseCase: GetHidePostEventUseCase,
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
            val posts: List<GalleryPostDto>,
            internal val currentRound: Int,
            internal val loadPage: Int,
        ) {
            val isInitPage: Boolean
                get() = (loadPage == 0)

            val currentTitleOption: TitleOption
                get() = titleOptions.getOrNull(currentRound - 1) ?: TitleOption()
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

        data class OnSelectTitleOption(
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
                hasNextPage = false,
                posts = emptyList(),
                currentRound = 1,
                loadPage = 0,
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
            is Intent.OnSelectTitleOption -> launch { handleOnSelectTitleOption(intent) }
            is Intent.OnClickFilter -> handleOnClickFilter()
            is Intent.OnFilterDismissRequest -> handleOnFilterDismissRequest()
            is Intent.OnClickFilterItem -> launch { handleOnClickSortFilter(intent) }
            is Intent.LoadNextPage -> launch { handleLoadNextPage() }
            is Intent.OnClickPost -> handleOnClickPost(intent)
        }
    }

    private suspend fun handleInit() {
        launch { collectRegisterPostEvent() }
        launch { collectHidePostEvent() }

        fetchInitData()
    }

    private suspend fun handleRefreshFeed() {
        reduce {
            copy(
                feedState = feedState.copy(
                    isRefreshing = true,
                    loadPage = 0,
                    posts = emptyList(),
                ),
            )
        }

        val paginationStatus = getRemoteGalleryPost()

        reduce {
            copy(
                feedState = feedState.copy(
                    isRefreshing = false,
                    paginationStatus = paginationStatus,
                ),
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

    private suspend fun handleOnSelectTitleOption(intent: Intent.OnSelectTitleOption) {
        val round = intent.round

        handleOnTitlePickerDismissRequest()

        if (round != currentState.feedState.currentRound) {
            reduce {
                copy(
                    feedState = feedState.copy(
                        currentRound = round,
                        posts = emptyList(),
                    ),
                )
            }

            val paginationStatus = getRemoteGalleryPost()

            reduce {
                copy(
                    feedState = feedState.copy(
                        paginationStatus = paginationStatus,
                    ),
                )
            }
        }
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
            reduce {
                copy(
                    feedState = feedState.copy(
                        postOrderType = postOrderType,
                        posts = emptyList(),
                    ),
                )
            }

            val paginationStatus = getRemoteGalleryPost()

            reduce {
                copy(
                    feedState = feedState.copy(
                        paginationStatus = paginationStatus,
                    ),
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

        val paginationStatus = getRemoteGalleryPost()

        reduce {
            copy(
                feedState = feedState.copy(
                    paginationStatus = paginationStatus,
                ),
            )
        }
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postId))
    }

    private suspend fun collectRegisterPostEvent() {
        getRegisterPostEventUseCase().collect {
            handleRefreshFeed()
        }
    }

    private suspend fun collectHidePostEvent() {
        getHidePostEventUseCase().collect { postId ->
            reduce {
                copy(
                    feedState = feedState.copy(
                        posts = feedState.posts.filter { it.postId != postId },
                    ),
                )
            }
        }
    }

    private suspend fun fetchInitData() {
        getTitleOptions()

        val paginationStatus = getRemoteGalleryPost()

        reduce {
            copy(
                feedState = feedState.copy(
                    paginationStatus = paginationStatus,
                ),
            )
        }
    }

    private suspend fun getTitleOptions() {
        val weeklyContestInfoListDto = getWeeklyContestInfoListUseCase().getOrNull()

        if (weeklyContestInfoListDto == null) {
            postSingleButtonDialogUseCase(type = CommonDialogType.NETWORK_ERROR)

            return
        }

        val titleOptions: List<TitleOption> =
            weeklyContestInfoListDto.weeklyContestInfoList.map {
                TitleOption(
                    round = it.round,
                    subject = it.subject,
                )
            }

        reduce {
            copy(
                feedState = feedState.copy(
                    titleOptions = titleOptions,
                ),
            )
        }
    }

    private suspend fun getRemoteGalleryPost(): PaginationStatus {
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
                ),
            )
        }

        val galleryDto = getFilteredGalleryByReportTargetIdsUseCase(
            round = state.currentRound,
            orderType = state.postOrderType.name,
            page = state.loadPage,
            pageSize = AppConst.Main.Gallery.PAGE_SIZE,
        ).getOrNull() ?: return PaginationStatus.FAILED

        reduce {
            copy(
                feedState = feedState.copy(
                    hasNextPage = galleryDto.hasNext,
                    posts = feedState.posts + galleryDto.posts,
                ),
            )
        }

        return PaginationStatus.SUCCESS
    }
}
