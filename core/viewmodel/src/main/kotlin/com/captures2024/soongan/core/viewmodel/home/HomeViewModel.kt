package com.captures2024.soongan.core.viewmodel.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.home.GetHomeUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val getHomeUseCase: GetHomeUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<HomeViewModel.State, HomeViewModel.Effect, HomeViewModel.Intent>(
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
        val contestInfo: ContestInfoDto = ContestInfoDto(),
        val postList: List<PostInfoDto> = emptyList(),
        val isWeeklySelected: Boolean = true,
        val isOpenBottomSheet: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("contestInfo", contestInfo.toString()),
            LogElementArgument("postList", postList.toString()),
            LogElementArgument("isWeeklySelected", isWeeklySelected.toString()),
            LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToRegistrationPost : Effect

        data object NavigateToHomeGallery : Effect

        data class NavigateToHomePost(
            val postInfo: PostInfoDto,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickPlus : Intent

        data class OnClickPost(
            val postInfo: PostInfoDto,
        ) : Intent

        data object OnToggleWeeklyDaily : Intent

        data object OnClickInfo : Intent

        data object OnCloseBottomSheet : Intent

        data object OnClickRightArrow : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }

            is Intent.OnClickPlus -> blockGuestModeLogic { handleOnClickPlus() }

            is Intent.OnClickPost -> handleOnClickPost(intent)

            is Intent.OnToggleWeeklyDaily -> handleOnToggleWeeklyDaily()

            is Intent.OnClickInfo -> handleOnClickInfo()

            is Intent.OnClickRightArrow -> handleOnClickRightArrow()

            is Intent.OnCloseBottomSheet -> handleOnCloseBottomSheet()
        }
    }

    private suspend fun handleInit() {
        val result = getHomeUseCase().getOrNull()

        if (result == null) {
            analyticsHelper.d(message = "result is null")
            return
        }

        val (contestInfo, postInfoList) = result

        reduce {
            copy(
                contestInfo = contestInfo,
                postList = postInfoList,
            )
        }
    }

    private fun handleOnClickPlus() {
        postSideEffect(Effect.NavigateToRegistrationPost)
    }

    private fun handleOnClickPost(intent: Intent.OnClickPost) {
        postSideEffect(Effect.NavigateToHomePost(intent.postInfo))
    }

    private fun handleOnToggleWeeklyDaily() {
        reduce {
            copy(
                isWeeklySelected = !isWeeklySelected,
            )
        }
    }

    private fun handleOnClickInfo() {
        reduce {
            copy(
                isOpenBottomSheet = true,
            )
        }
    }

    private fun handleOnClickRightArrow() {
        postSideEffect(Effect.NavigateToHomeGallery)
    }

    private fun handleOnCloseBottomSheet() {
        reduce {
            copy(
                isOpenBottomSheet = false,
            )
        }
    }
}
