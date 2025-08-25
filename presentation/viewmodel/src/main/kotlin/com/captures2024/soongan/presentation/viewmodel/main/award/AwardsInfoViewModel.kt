package com.captures2024.soongan.presentation.viewmodel.main.award

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.core.navigator.screen.main.awards.AwardsInfoNavigator
import com.captures2024.soongan.domain.usecase.awards.GetAwardsInfoUseCase
import com.captures2024.soongan.domain.usecase.contest.GetHidePostEventUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AwardsInfoViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getHidePostEventUseCase: GetHidePostEventUseCase,
    private val getAwardsInfoUseCase: GetAwardsInfoUseCase,
) : BaseViewModel<AwardsInfoViewModel.State, AwardsInfoViewModel.Effect, AwardsInfoViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val roundId: Long,
        val initState: InitState,
        val awardsInfo: AwardsDetailDto?,
    ) : UIState {

        enum class InitState {
            INIT,
            SUCCESS,
            FAIL,
        }
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateToPost(
            val postId: Long,
        ) : Effect

        data class NavigateToFeed(
            val round: Long,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickBack : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

        data class OnClickAllPosts(
            val round: Long,
        ) : Intent

        data object OnClickRetry : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val route = savedStateHandle.toRoute<AwardsInfoNavigator>()

        return State(
            roundId = route.id,
            initState = State.InitState.INIT,
            awardsInfo = null,
        )
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }

            is Intent.OnClickBack -> handleOnClickBack()

            is Intent.OnClickPost -> handleOnClickPost(intent)

            is Intent.OnClickAllPosts -> handleOnClickAllPosts(intent)

            is Intent.OnClickRetry -> loadingLaunch { handleOnClickRetry() }
        }
    }

    private suspend fun handleInit() {
        launch { collectHidePostEvent() }

        val awardsInfo = getAwardsInfoUseCase(
            awardsId = currentState.roundId,
        ).getOrNull()

        reduce {
            copy(
                initState = when (awardsInfo) {
                    null -> State.InitState.FAIL
                    else -> State.InitState.SUCCESS
                },
                awardsInfo = awardsInfo,
            )
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToPost(intent.postId))
    }

    private fun handleOnClickAllPosts(intent: Intent.OnClickAllPosts) {
        postSideEffect(Effect.NavigateToFeed(intent.round))
    }

    private suspend fun handleOnClickRetry() {
        handleInit()
    }

    private suspend fun collectHidePostEvent() {
        getHidePostEventUseCase().collect { postId ->
            val currentAwardsInfo = currentState.awardsInfo

            if (currentAwardsInfo == null) {
                return@collect
            }

            reduce {
                copy(
                    awardsInfo = currentAwardsInfo.copy(
                        prizePosts = currentAwardsInfo.prizePosts.map {
                            it.copy(
                                status = when {
                                    postId == it.postId -> AwardsPostStatusType.DELETED_BY_CREATOR
                                    else -> it.status
                                },
                            )
                        },
                    ),
                )
            }
        }
    }
}
