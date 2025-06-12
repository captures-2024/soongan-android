package com.captures2024.soongan.presentation.viewmodel.main.award

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.domain.usecase.contest.GetHidePostEventUseCase
import com.captures2024.soongan.domain.usecase.contest.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

// TODO move package and convert remote value class
data class ContestInfo(
    val round: Int = 1,
    val subject: String = "주제",
    val startAt: String = "2025.01.07",
    val endAt: String = "2025.02.07",
    val allPostCount: Int = 30,
)

data class TopPost(
    val postId: Long = Random.nextLong(),
    val imageUrl: String = "https://storage.googleapis.com/soongan-dev-bk/1/weekly/1/test.jpeg",
    val nickname: String = "닉네임",
    val voteLike: Int = 9999,
)

data class WinnerPost(
    val topPost: TopPost = TopPost(),
    val title: String = "제목",
    val isDefaultOrientation: Boolean = true,
)

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
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
    private val getHidePostEventUseCase: GetHidePostEventUseCase,
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
        val initState: InitState,
        val contestInfo: ContestInfo,
        val topPosts: List<TopPost>,
        val winnerPost: WinnerPost,
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

        data object NavigateToFeed : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickBack : Intent

        data class OnClickPost(
            val postId: Long,
        ) : Intent

        data object OnClickAllPosts : Intent

        data object OnClickRetry : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
//        val route = savedStateHandle.toRoute<AwardsInfoNavigator>()

        return State(
            initState = State.InitState.INIT,
            winnerPost = WinnerPost(),
            contestInfo = ContestInfo(),
            topPosts = emptyList(),
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

            is Intent.OnClickAllPosts -> handleOnClickAllPosts()

            is Intent.OnClickRetry -> loadingLaunch { handleOnClickRetry() }
        }
    }

    private suspend fun handleInit() {
        launch { collectHidePostEvent() }

        val weeklyContestInfoListDto = getWeeklyContestInfoListUseCase().getOrNull()

        if (weeklyContestInfoListDto == null) {
            reduce {
                copy(
                    initState = State.InitState.FAIL,
                )
            }

            return
        }

        val weeklyContestInfo = weeklyContestInfoListDto.weeklyContestInfoList.first()
        val contestInfo = ContestInfo(
            round = weeklyContestInfo.round,
            subject = weeklyContestInfo.subject,
            startAt = weeklyContestInfo.startAt,
            endAt = weeklyContestInfo.endAt,
            allPostCount = 30,
        )

        reduce {
            copy(
                initState = State.InitState.SUCCESS,
                contestInfo = contestInfo,
                topPosts = List(6) { TopPost() },
            )
        }

        analyticsHelper.d { "initState: ${state.value.initState.name}, contestInfo: $contestInfo, topPosts:${currentState.topPosts}" }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToPost(intent.postId))
    }

    private fun handleOnClickAllPosts() {
        postSideEffect(Effect.NavigateToFeed)
    }

    private suspend fun handleOnClickRetry() {
        handleInit()
    }

    private suspend fun collectHidePostEvent() {
        getHidePostEventUseCase().collect { postId ->
            // TODO(hidden post 처리 - display text or blur)
        }
    }
}
