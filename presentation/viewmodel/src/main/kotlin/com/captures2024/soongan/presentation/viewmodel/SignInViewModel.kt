package com.captures2024.soongan.presentation.viewmodel

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
import com.captures2024.soongan.core.domain.usecase.system.LaunchPrivacyPolicyUseCase
import com.captures2024.soongan.core.domain.usecase.system.LaunchTermsUseCase
import com.captures2024.soongan.core.model.exception.UIException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val setGuestModeUseCase: SetGuestModeUseCase,
    private val launchTermsUseCase: LaunchTermsUseCase,
    private val launchPrivacyPolicyUseCase: LaunchPrivacyPolicyUseCase,
    private val getFcmUseCase: GetFcmUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
) : BaseViewModel<SignInViewModel.State, SignInViewModel.Effect, SignInViewModel.Intent>(
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
        data object NavigateToSignUp : Effect
    }

    sealed interface Intent : UIIntent {
        data object OnClickGuestMode : Intent

        data object OnClickTerms : Intent

        data object OnClickPrivacyPolicy : Intent

        data class OnClickSignGoogle(
            val loginCallback: suspend () -> String?,
        ) : Intent

        data class CompleteSignKakao(
            val accessToken: String?,
            val refreshToken: String?,
        ) : Intent

        data class OnFailedSignKakao(
            val throwable: Throwable?,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickGuestMode -> handleOnClickGuestMode()
            is Intent.OnClickTerms -> loadingLaunch { handleOnClickTerms() }
            is Intent.OnClickPrivacyPolicy -> loadingLaunch { handleOnClickPrivacyPolicy() }
            is Intent.OnClickSignGoogle -> loadingLaunch { handleOnClickSignGoogle(intent) }
            is Intent.CompleteSignKakao -> loadingLaunch { handleCompleteSignKakao(intent) }
            is Intent.OnFailedSignKakao -> handleOnFailedSignKakao(intent)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "createInitialState - currentState: $currentState" }
    }

    private fun handleOnClickGuestMode() {
        setGuestModeUseCase(isGuestMode = true)
    }

    private suspend fun handleOnClickTerms() {
        launchTermsUseCase()
    }

    private suspend fun handleOnClickPrivacyPolicy() {
        launchPrivacyPolicyUseCase()
    }

    private suspend fun handleOnClickSignGoogle(intent: Intent.OnClickSignGoogle) {
        val token = intent.loginCallback() ?: return
        googleSignIn(token)
    }

    private suspend fun handleCompleteSignKakao(intent: Intent.CompleteSignKakao) {
        if (intent.accessToken == null) {
            return
        }

        kakaoSignIn(token = intent.accessToken)
    }

    private fun handleOnFailedSignKakao(intent: Intent.OnFailedSignKakao) {
        analyticsHelper.e(intent.throwable) { "handleOnFailedSignKakao - state: $currentState" }
    }

    private suspend fun kakaoSignIn(token: String) {
        val fcmToken = getFcmUseCase().getOrNull()

        if (fcmToken == null) {
            analyticsHelper.d { "fcm token is null" }
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

            }

            else -> {
                analyticsHelper.d { "kakaoSignIn - result: $result" }
            }
        }
    }

    private suspend fun googleSignIn(token: String) {
        val fcmToken = getFcmUseCase().getOrNull()

        if (fcmToken == null) {
            analyticsHelper.d { "fcm token is null" }
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
            }

            else -> {
                analyticsHelper.d { "googleSign - result: $result" }
            }
        }
    }
}
