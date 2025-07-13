package com.captures2024.soongan.presentation.viewmodel.main.award

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.domain.usecase.awards.GetAwardsListUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AwardsViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getAwardsListUseCase: GetAwardsListUseCase,
) : BaseViewModel<AwardsViewModel.State, AwardsViewModel.Effect, AwardsViewModel.Intent>(
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
        val awardsList: List<AwardsDefaultDto>,
    ) : UIState {

        enum class InitState {
            INIT,
            SUCCESS,
            FAIL,
        }
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToAwardsInfo(
            val roundId: Long,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data class OnClickContestSubject(
            val awards: AwardsDefaultDto,
        ) : Intent

        data object OnClickEntry : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            initState = State.InitState.INIT,
            awardsList = emptyList(),
        )
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }

            is Intent.OnClickContestSubject -> handleOnClickContestSubject(intent)

            is Intent.OnClickEntry -> loadingLaunch { handleOnClickEntry() }
        }
    }

    private suspend fun handleInit() {
        val awardsList = getAwardsListUseCase().getOrNull()

        if (awardsList == null) {
            reduce {
                copy(
                    initState = State.InitState.FAIL,
                )
            }
            return
        }

        reduce {
            copy(
                initState = State.InitState.SUCCESS,
                awardsList = awardsList,
            )
        }
    }

    private fun handleOnClickContestSubject(intent: Intent.OnClickContestSubject) {
        postSideEffect(
            sideEffect = Effect.NavigateToAwardsInfo(
                roundId = intent.awards.id,
            ),
        )
    }

    private suspend fun handleOnClickEntry() {
        handleInit()
    }
}
