package com.captures2024.soongan.feature.signIn

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInformationUseCase
import com.captures2024.soongan.core.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.captures2024.soongan.feature.signIn.state.SignInSideEffect
import com.captures2024.soongan.feature.signIn.state.SignInUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val initFcmUseCase: InitFcmUseCase,
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val getMemberInformationUseCase: GetMemberInformationUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignInUIState, SignInSideEffect, SignInIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SignInUIState = SignInUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override suspend fun handleIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.OnClickSignGoogle -> googleSignIn()

            is SignInIntent.OnClickSignKakao -> kakaoSignIn()

            is SignInIntent.CanceledSignGoogle,
            is SignInIntent.CanceledSignKakao -> canceledSignIn()

            is SignInIntent.FailedSignGoogle,
            is SignInIntent.FailedSignKakao -> failedSignIn()

            is SignInIntent.CompleteSignGoogle -> googleSignIn(token = intent.token)

            is SignInIntent.CompleteSignKakao -> kakaoSignIn(token = intent.accessToken)

            is SignInIntent.FetchFCMToken -> fetchFcmToken(token = intent.token)

            is SignInIntent.OnClickGuestMode -> onClickGuestMode()

            is SignInIntent.OnClickPrivacyPolicy -> onClickPrivacyPolicy()

            is SignInIntent.OnClickTermsOfUse -> onClickTermsOfUse()
        }
    }

    private fun canceledSignIn() {
        reduce {
            copy(isLoading = false)
        }
    }

    private fun failedSignIn() {
        reduce {
            copy(isLoading = false)
        }
    }

    private fun googleSignIn() {
        reduce {
            copy(isLoading = true)
        }
        postSideEffect(SignInSideEffect.GoogleSignIn)
    }

    private fun googleSignIn(token: String) = launch {
        val result = signingGoogleUseCase(
            token = token,
            fcmToken = currentState.fcmToken
        ).getOrNull()

        if (result == null) {
            analyticsHelper.d(message = "result is null")
            intent(SignInIntent.FailedSignGoogle)
            return@launch
        }

        analyticsHelper.d(message = "result = $result")

        when (result) {
            true -> isAllowCheck()
            false -> failedSignIn()
        }
    }

    private fun kakaoSignIn() {
        reduce {
            copy(isLoading = true)
        }
        postSideEffect(SignInSideEffect.KakaoSignIn)
    }

    private fun kakaoSignIn(token: String) = launch {
        val result = signingKakaoUseCase(
            token = token,
            fcmToken = currentState.fcmToken,
        ).getOrNull()

        if (result == null) {
            analyticsHelper.d(
                message = "result is null",
            )
            intent(SignInIntent.FailedSignKakao)
            return@launch
        }

        analyticsHelper.d(
            message = "result = $result",
        )

        when (result) {
            true -> isAllowCheck()

            false -> failedSignIn()
        }
    }

    private fun fetchFcmToken(token: String) {
        reduce {
            copy(
                fcmToken = token
            )
        }

        launch {
            initFcmUseCase(fcmToken = token)
        }
    }

    private fun onClickGuestMode() {
        TODO("Not Impl yet")
    }

    private fun onClickPrivacyPolicy() {
        postSideEffect(SignInSideEffect.NavigateToPrivacyPolicy)
    }

    private fun onClickTermsOfUse() {
        postSideEffect(SignInSideEffect.NavigateToTermsOfUse)
    }

    private fun isAllowCheck() {
        launch {
            val result = getMemberInformationUseCase().getOrNull() ?: return@launch intent(SignInIntent.FailedSignGoogle)

//            when (result) {
//                true -> postSideEffect(SignInSideEffect.NavigateToMain)
//                false -> postSideEffect(SignInSideEffect.NavigateToSignUp)
//            }

            reduce {
                copy(
                    isLoading = false
                )
            }
        }
    }
}