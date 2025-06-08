package com.captures2024.soongan.presentation.viewmodel.main.award

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.domain.usecase.contest.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// TODO move package and convert remote value class
data class AwardsContestInfo(
    val round: Int = 1,
    val subject: String = "주제",
    val imageUrl: String = "https://storage.googleapis.com/soongan-dev-bk/1/weekly/1/test.jpeg",
)

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
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
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
        val awardsContestInfoList: List<AwardsContestInfo>,
    ) : UIState {

        enum class InitState {
            INIT,
            NO_CONTEST,
            SUCCESS,
            FAIL,
        }
    }

    sealed interface Effect : UISideEffect {

        data class NavigateToAwardsInfo(
            val round: Int,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data class OnClickContestSubject(
            val round: Int,
        ) : Intent

        data object OnClickEntry : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            initState = State.InitState.INIT,
            awardsContestInfoList = emptyList(),
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
        val weeklyContestInfoListDto = getWeeklyContestInfoListUseCase().getOrNull()

        if (weeklyContestInfoListDto == null) {
            reduce {
                copy(
                    initState = State.InitState.FAIL,
                )
            }

            return
        }

//        if (weeklyContestInfoListDto.weeklyContestInfoList.first().endAt < now)
//        initState = State.InitState.NO_CONTEST

        val awardsContestInfoList: List<AwardsContestInfo> =
            weeklyContestInfoListDto.weeklyContestInfoList.map {
                AwardsContestInfo(
                    round = it.round,
                    subject = it.subject,
                )
            }

        reduce {
            copy(
                initState = State.InitState.SUCCESS,
                awardsContestInfoList = awardsContestInfoList,
            )
        }
    }

    private fun handleOnClickContestSubject(intent: Intent.OnClickContestSubject) {
        postSideEffect(Effect.NavigateToAwardsInfo(intent.round))
    }

    private suspend fun handleOnClickEntry() {
        handleInit()
    }
}
