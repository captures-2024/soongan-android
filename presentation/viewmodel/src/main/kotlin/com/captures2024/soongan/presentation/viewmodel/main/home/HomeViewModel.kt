package com.captures2024.soongan.presentation.viewmodel.main.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.home.GetHomeUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getHomeUseCase: GetHomeUseCase,
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
) : BaseViewModel<HomeViewModel.State, HomeViewModel.Effect, HomeViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val initState: InitState,
        val contestInfo: HomeContestInfoDto,
        val postInfoList: List<PostInfoDto>,
        val maxRegisterPostCount: Int,
        val isShowContestInfoBottomSheet: Boolean,
    ) : UIState {

        enum class InitState {
            INIT,
            LOADING,
            SUCCESS,
            FAIL,
        }
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToRegister : Effect

        data object NavigateToPostList : Effect

        data class NavigateToPost(
            val postInfoDto: PostInfoDto,
        ) : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object ViewOnResume : Intent

        data object OnClickContestInfo : Intent

        data object OnClickPostList : Intent

        data object OnClickRegister : Intent

        data class OnClickPost(
            val postInfoDto: PostInfoDto,
        ) : Intent

        data object OnClickRetry : Intent

        data object DismissContestInfoBottomSheet : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        initState = State.InitState.INIT,
        contestInfo = HomeContestInfoDto(
            contestType = AppConst.EMPTY_STRING,
            subject = AppConst.EMPTY_STRING,
            startAt = AppConst.EMPTY_STRING,
            endAt = AppConst.EMPTY_STRING,
        ),
        postInfoList = emptyList(),
        maxRegisterPostCount = AppConst.Main.Home.MAX_REGISTER_POST_COUNT,
        isShowContestInfoBottomSheet = false,
    )

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.ViewOnResume -> launch { handleViewOnResume() }
            is Intent.OnClickContestInfo -> handleOnClickContestInfo()
            is Intent.OnClickPostList -> handleOnClickPostList()
            is Intent.OnClickRegister -> handleOnClickRegister()
            is Intent.OnClickPost -> handleOnClickPost(intent)
            is Intent.OnClickRetry -> loadingLaunch { handleOnClickRetry() }
            is Intent.DismissContestInfoBottomSheet -> handleDismissContestInfoBottomSheet()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        syncHomeInfo()
    }

    private suspend fun handleViewOnResume() {
        val state = currentState

        when (state.initState) {
            State.InitState.INIT,
            State.InitState.LOADING,
                -> {
                    analyticsHelper.d { "handleViewOnResume - state.isInit: ${state.initState}" }
                    return
                }
            else -> Unit
        }

        syncHomeInfo()
    }

    private fun handleOnClickContestInfo() {
        showContestInfoBottomSheet()
    }

    private fun handleOnClickPostList() {
        postSideEffect(Effect.NavigateToPostList)
    }

    private fun handleOnClickRegister() {
        postSideEffect(Effect.NavigateToRegister)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToPost(intent.postInfoDto))
    }

    private suspend fun handleOnClickRetry() {
        syncHomeInfo()
    }

    private fun handleDismissContestInfoBottomSheet() {
        dismissContestInfoBottomSheet()
    }

    private suspend fun syncHomeInfo() {
        reduce {
            copy(
                initState = State.InitState.LOADING,
            )
        }

        val result = getHomeUseCase().getOrNull()

        if (result == null) {
            analyticsHelper.d { "handleInit - result is null" }

            reduce {
                copy(
                    initState = State.InitState.FAIL,
                )
            }

            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        val contestInfoDto = result.first
        val postInfoList = result.second

        reduce {
            copy(
                initState = State.InitState.SUCCESS,
                contestInfo = contestInfoDto,
                postInfoList = postInfoList,
            )
        }
    }

    private fun showContestInfoBottomSheet() {
        reduce {
            copy(
                isShowContestInfoBottomSheet = true,
            )
        }
    }

    private fun dismissContestInfoBottomSheet() {
        reduce {
            copy(
                isShowContestInfoBottomSheet = false,
            )
        }
    }
}
