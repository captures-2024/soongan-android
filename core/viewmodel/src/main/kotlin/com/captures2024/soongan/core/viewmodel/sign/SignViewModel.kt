package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.members.SetGuestModeUseCase
import com.captures2024.soongan.core.model.exception.UIException
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getFcmUseCase: GetFcmUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val setGuestModeUseCase: SetGuestModeUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
) : NewBaseViewModel<SignViewModel.State, SignViewModel.Effect, SignViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data object State : UIState

    sealed interface Effect : UISideEffect {

        sealed interface Navigate : Effect {
            data object NavigateToTermsOfUse : Navigate

            data object NavigateToPrivacyPolicy : Navigate

            data class NavigateToSignUp(
                val nickname: String?,
            ) : Navigate
        }

        sealed interface UI : Effect {
            data class ShowToast(
                val exception: UIException,
            ) : UI
        }
    }

    sealed interface Intent : UIIntent {
        data object OnClickGuestMode : Intent

        data class OnClickSignGoogle(
            val loginCallback: suspend () -> String?,
        ) : Intent

        data object OnClickTermsOfUse : Intent

        data object OnClickPrivacyPolicy : Intent

        data class CompleteSignKakao(
            val accessToken: String?,
            val refreshToken: String?,
        ) : Intent

        data class OnFailedSignKakao(
            val throwable: Throwable?,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickGuestMode -> handleOnClickGuestMode()
            is Intent.OnClickSignGoogle -> loadingLaunch { handleOnClickSignGoogle(intent) }
            is Intent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()
            is Intent.OnClickTermsOfUse -> handleOnClickTermsOfUse()
            is Intent.CompleteSignKakao -> launch { handleCompleteSignKakao(intent) }
            is Intent.OnFailedSignKakao -> handleOnFailedSignKakao(intent)
        }
    }

    private fun handleOnClickGuestMode() {
        setGuestModeUseCase(isGuestMode = true)
    }

    private suspend fun handleOnClickSignGoogle(intent: Intent.OnClickSignGoogle) {
        val token = intent.loginCallback() ?: return
        googleSignIn(token)
    }

    private fun handleOnClickPrivacyPolicy() {
        postSideEffect(Effect.Navigate.NavigateToPrivacyPolicy)
    }

    private fun handleOnClickTermsOfUse() {
        postSideEffect(Effect.Navigate.NavigateToTermsOfUse)
    }

    private suspend fun handleCompleteSignKakao(intent: Intent.CompleteSignKakao) {
        if (intent.accessToken == null) {
            return
        }

        kakaoSignIn(token = intent.accessToken)
    }

    private fun handleOnFailedSignKakao(intent: Intent.OnFailedSignKakao) {
        analyticsHelper.e(intent.throwable) { "state: $currentState" }

        postSideEffect(Effect.UI.ShowToast(UIException.SignException(intent.throwable)))
    }

    private suspend fun kakaoSignIn(token: String) {
        val fcmToken = getFcmUseCase().getOrNull()

        if (fcmToken == null) {
            analyticsHelper.d { "fcm token is null" }
            postSideEffect(Effect.UI.ShowToast(UIException.SignException()))
            return
        }

        val result = signingKakaoUseCase(
            token = token,
            fcmToken = fcmToken,
        ).getOrNull()

        when (result) {
            true -> {
                val infoDto = getMemberInfoUseCase().getOrNull()

                if (infoDto == null) {
                    analyticsHelper.d { "kakaoSignIn - infoDto is null" }
                }

                postSideEffect(Effect.Navigate.NavigateToSignUp(infoDto?.nickname))
            }

            else -> {
                analyticsHelper.d { "kakaoSignIn - result: $result" }

                postSideEffect(Effect.UI.ShowToast(UIException.SignException()))
            }
        }
    }

    private suspend fun googleSignIn(token: String) {
        val fcmToken = getFcmUseCase().getOrNull()

        if (fcmToken == null) {
            analyticsHelper.d { "fcm token is null" }
            postSideEffect(Effect.UI.ShowToast(UIException.SignException()))
            return
        }

        val result = signingGoogleUseCase(
            token = token,
            fcmToken = fcmToken,
        ).getOrNull()

        when (result) {
            true -> {
                val infoDto = getMemberInfoUseCase().getOrNull()

                if (infoDto == null) {
                    analyticsHelper.d { "googleSign - infoDto is null" }
                }

                postSideEffect(Effect.Navigate.NavigateToSignUp(infoDto?.nickname))
            }

            else -> {
                analyticsHelper.d { "googleSign - result: $result" }

                postSideEffect(Effect.UI.ShowToast(UIException.SignException()))
            }
        }
    }
}
