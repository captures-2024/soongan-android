package com.captures2024.soongan.presentation.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.model.exception.NetworkExceptionWrapper
import com.captures2024.soongan.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.member.GetMemberInfoUseCase
import com.captures2024.soongan.domain.usecase.member.SetGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchPrivacyPolicyUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchTermsUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
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
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
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
        ).getOrElse(this::failedSignInCase)

        if (result) {
            val infoDto = getMemberInfoUseCase().getOrNull()

            if (infoDto == null) {
                analyticsHelper.d { "kakaoSignIn - infoDto is null" }

                return
            }

            when {
                infoDto.nickname == null || infoDto.birthYear == null -> postSideEffect(Effect.NavigateToSignUp)
                else -> Unit
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
        ).getOrElse(this::failedSignInCase)

        if (result) {
            val infoDto = getMemberInfoUseCase().getOrNull()

            if (infoDto == null) {
                analyticsHelper.d { "googleSign - infoDto is null" }
                return
            }

            when {
                infoDto.nickname == null || infoDto.birthYear == null -> postSideEffect(Effect.NavigateToSignUp)
                else -> Unit
            }
        }
    }

    private fun failedSignInCase(exception: Throwable): Boolean {
        analyticsHelper.d { "failedSignInCase - exception: $exception" }

        if (exception is NetworkExceptionWrapper) {
            launch {
                postSingleButtonDialogUseCase(
                    when (exception.statusCode) {
                        705 -> CommonDialogType.OTHER_SOCIAL

                        else -> CommonDialogType.NETWORK_ERROR
                    }
                )
            }
        }

        return false
    }
}
