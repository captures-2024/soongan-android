package com.captures2024.soongan.presentation.viewmodel.main.home

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
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val getFilteredGalleryByReportTargetIdsUseCase: GetFilteredGalleryByReportTargetIdsUseCase,
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
) : BaseViewModel<HomeGalleryViewModel.State, HomeGalleryViewModel.Effect, HomeGalleryViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isRefreshing: Boolean,
        val round: Int?,
        val subject: String,
        val posts: List<GalleryPostDto>,
        val orderType: PostOrderType,
        val paginationStatus: PaginationStatus,
        val loadPage: Int,
        val loadPageSize: Int,
        val hasNextPage: Boolean,
        val isShowFilterBottomSheet: Boolean,
    ) : UIState {
        val isInitPage: Boolean
            get() = loadPage == 0
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateToPost(
            val postId: Long,
        ) : Effect

        data object NavigateToRegisterPost : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickBack : Intent

        data object OnClickFilter : Intent

        data object OnRefresh : Intent

        data object OnLoadNextPage : Intent

        data object OnClickRegisterPost : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

        data object OnDismissRequestFilterBottomSheet : Intent

        data class OnClickFilterItem(
            val selectedOrderType: PostOrderType,
        ) : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        isRefreshing = false,
        round = null,
        subject = AppConst.EMPTY_STRING,
        posts = emptyList(),
        orderType = PostOrderType.MOST_LIKED,
        paginationStatus = PaginationStatus.DEFAULT,
        loadPage = 0,
        loadPageSize = 50,
        hasNextPage = false,
        isShowFilterBottomSheet = false,
    )

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickFilter -> handleOnClickFilter()
            is Intent.OnRefresh -> launch { handleOnRefresh() }
            is Intent.OnLoadNextPage -> launch { handleOnLoadNextPage() }
            is Intent.OnClickRegisterPost -> handleOnClickRegisterPost()
            is Intent.OnClickPost -> handleOnClickPost(intent)
            is Intent.OnDismissRequestFilterBottomSheet -> handleOnDismissRequestFilterBottomSheet()
            is Intent.OnClickFilterItem -> loadingLaunch { handleOnClickFilterItem(intent) }
        }
    }

    private suspend fun handleInit() {
        val weeklyInfo = getWeeklyContestInfoListUseCase
            .invoke()
            .getOrNull()
            ?.weeklyContestInfoList
            ?.lastOrNull()

        if (weeklyInfo == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
        }

        reduce {
            copy(
                round = weeklyInfo?.round,
                subject = weeklyInfo?.subject ?: AppConst.EMPTY_STRING,
            )
        }

        val status = getRemotePost(
            page = 0,
            isRefreshing = true,
        )

        reduce {
            copy(
                paginationStatus = status,
            )
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickFilter() {
        showFilterBottomSheet()
    }

    private suspend fun handleOnRefresh() {
        reduce {
            copy(
                isRefreshing = true,
            )
        }

        val status = getRemotePost(
            page = 0,
            isRefreshing = true,
        )

        reduce {
            copy(
                isRefreshing = false,
                paginationStatus = status,
            )
        }
    }

    private suspend fun handleOnLoadNextPage() {
        val status = getRemotePost(page = currentState.loadPage)

        reduce {
            copy(
                paginationStatus = status,
            )
        }
    }

    private fun handleOnClickRegisterPost() {
        postSideEffect(Effect.NavigateToRegisterPost)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(
            sideEffect = Effect.NavigateToPost(
                postId = intent.postId,
            ),
        )
    }

    private fun handleOnDismissRequestFilterBottomSheet() {
        dismissFilterBottomSheet()
    }

    private suspend fun handleOnClickFilterItem(intent: Intent.OnClickFilterItem) {
        dismissFilterBottomSheet()

        reduce {
            copy(
                posts = emptyList(),
                orderType = intent.selectedOrderType,
                paginationStatus = PaginationStatus.DEFAULT,
                loadPage = 0,
                hasNextPage = false,
            )
        }

        val status = getRemotePost(
            page = 0,
            isRefreshing = true,
        )

        reduce {
            copy(
                paginationStatus = status,
            )
        }
    }

    private suspend fun getRemotePost(
        page: Int,
        isRefreshing: Boolean = false,
    ): PaginationStatus {
        val state = currentState

        when (state.paginationStatus) {
            PaginationStatus.REFRESH_LOAD,
            PaginationStatus.PAGING_LOAD,
            -> return state.paginationStatus

            else -> Unit
        }

        reduce {
            copy(
                paginationStatus = when (isRefreshing) {
                    true -> PaginationStatus.REFRESH_LOAD
                    false -> PaginationStatus.PAGING_LOAD
                },
            )
        }

        val galleryDto = getFilteredGalleryByReportTargetIdsUseCase(
            params = GetFilteredGalleryByReportTargetIdsUseCase.Params(
                round = state.round,
                orderType = state.orderType.name,
                page = page,
                pageSize = state.loadPageSize,
            ),
        ).getOrNull()

        if (galleryDto == null) {
            return PaginationStatus.FAILED
        }

        reduce {
            copy(
                posts = when (isRefreshing) {
                    true -> galleryDto.posts
                    false -> posts + galleryDto.posts
                },
                loadPage = page + 1,
                hasNextPage = galleryDto.hasNext,
            )
        }

        return PaginationStatus.SUCCESS
    }

    private fun showFilterBottomSheet() {
        reduce {
            copy(
                isShowFilterBottomSheet = true,
            )
        }
    }

    private fun dismissFilterBottomSheet() {
        reduce {
            copy(
                isShowFilterBottomSheet = false,
            )
        }
    }
}
