package com.captures2024.soongan.presentation.viewmodel.main.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.domain.usecase.contest.GetHidePostEventUseCase
import com.captures2024.soongan.domain.usecase.contest.GetRegisterPostEventUseCase
import com.captures2024.soongan.domain.usecase.home.GetHomeUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.enums.HomeInfoState
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
    private val getRegisterPostEventUseCase: GetRegisterPostEventUseCase,
    private val getHidePostEventUseCase: GetHidePostEventUseCase,
    private val getHomeUseCase: GetHomeUseCase,
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
        val isLoading: Boolean = false,
        val homeInfo: HomeInfo = HomeInfo(),
        val isShowContestInfoBottomSheet: Boolean = false,
    ) : UIState {

        data class HomeInfo(
            val homeInfoState: HomeInfoState = HomeInfoState.INIT,
            val homeContestInfo: HomeContestInfoDto? = null,
            val postInfos: List<PostInfoDto>? = null,
            val maxRegisterPostCount: Int = AppConst.Main.Home.MAX_REGISTER_POST_COUNT,
        )
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToRegister : Effect

        data object NavigateToPostList :
            Effect

        data class NavigateToPost(
            val postInfoDto: PostInfoDto,
        ) : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init :
            Intent

        data object OnResumeView :
            Intent

        data object OnClickContestInfo :
            Intent

        data object OnClickPostList :
            Intent

        data object OnClickRegister :
            Intent

        data class OnClickPost(
            val postInfoDto: PostInfoDto,
        ) : Intent

        data object OnClickRetry :
            Intent

        data object DismissContestInfoBottomSheet :
            Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OnResumeView -> isRunBlock { loadingLaunch { handleOnResumeView() } }
            is Intent.OnClickContestInfo -> isRunBlock { handleOnClickContestInfo() }
            is Intent.OnClickPost -> isRunBlock { handleOnClickPost(intent) }
            is Intent.OnClickPostList -> isRunBlock { handleOnClickPostList() }
            is Intent.OnClickRegister -> isRunBlock { handleOnClickRegister() }
            is Intent.OnClickRetry -> isRunBlock { loadingLaunch { handleOnClickRetry() } }
            is Intent.DismissContestInfoBottomSheet -> isRunBlock { handleDismissContestInfoBottomSheet() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        launch { collectRegisterPostEvent() }
        launch { collectHidePostEvent() }

        val homeInfo = fetchHomeData()

        reduce {
            copy(
                isLoading = false,
                homeInfo = homeInfo,
            )
        }
    }

    private suspend fun handleOnResumeView() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        val homeInfo = fetchHomeData()

        reduce {
            copy(
                isLoading = false,
                homeInfo = homeInfo,
            )
        }
    }

    private fun handleOnClickContestInfo() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        showContestInfoBottomSheet()

        reduce {
            copy(
                isLoading = false,
            )
        }
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        reduce {
            copy(
                isLoading = true,
            )
        }

        postSideEffect(Effect.NavigateToPost(intent.postInfoDto))

        reduce {
            copy(
                isLoading = false,
            )
        }
    }

    private fun handleOnClickPostList() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        postSideEffect(Effect.NavigateToPostList)

        reduce {
            copy(
                isLoading = false,
            )
        }
    }

    private fun handleOnClickRegister() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        blockGuestModeLogic {
            postSideEffect(Effect.NavigateToRegister)
        }

        reduce {
            copy(
                isLoading = false,
            )
        }
    }

    private suspend fun handleOnClickRetry() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        val homeInfo = fetchHomeData()

        reduce {
            copy(
                isLoading = false,
                homeInfo = homeInfo,
            )
        }
    }

    private fun handleDismissContestInfoBottomSheet() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        dismissContestInfoBottomSheet()

        reduce {
            copy(
                isLoading = false,
            )
        }
    }

    private inline fun isRunBlock(block: () -> Unit) {
        if (currentState.isLoading) {
            return
        }

        block()
    }

    private suspend fun collectRegisterPostEvent() {
        getRegisterPostEventUseCase().collect {
            reduce {
                copy(
                    isLoading = true,
                )
            }

            val homeInfo = fetchHomeData()

            reduce {
                copy(
                    isLoading = false,
                    homeInfo = homeInfo,
                )
            }
        }
    }

    private suspend fun collectHidePostEvent() {
        getHidePostEventUseCase().collect { postId ->
            reduce {
                copy(
                    homeInfo = homeInfo.copy(
                        postInfos = homeInfo.postInfos?.filter { it.postId != postId },
                    ),
                )
            }
        }
    }

    private suspend fun fetchHomeData(): State.HomeInfo {
        val (homeContestInfo: HomeContestInfoDto, postInfos: List<PostInfoDto>) = getHomeUseCase()
            .getOrElse { throwable ->
                analyticsHelper.d { "failFetchHomeData - throwable: $throwable" }

                // TODO empty state
                val state = HomeInfoState.ERROR

                return State.HomeInfo(
                    homeInfoState = state,
                    homeContestInfo = null,
                    postInfos = null,
                )
            }

        return State.HomeInfo(
            homeInfoState = HomeInfoState.SUCCESS,
            homeContestInfo = homeContestInfo,
            postInfos = postInfos,
        )
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
