package com.captures2024.soongan.feature.signIn

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.core.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.captures2024.soongan.feature.signIn.state.SignInSideEffect
import com.captures2024.soongan.feature.signIn.state.SignInUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val clearAllTokenUseCase: ClearAllTokenUseCase,
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
            is SignInIntent.FetchFcmToken -> handleFetchFcmToken(intent)

            is SignInIntent.OnClickSignGoogle -> handleOnClickSignGoogle()

            is SignInIntent.OnClickSignKakao -> handleOnClickSignKakao()

            is SignInIntent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()

            is SignInIntent.OnClickTermsOfUse -> handleOnClickTermsOfUse()

            is SignInIntent.CanceledSignGoogle -> handleCanceledSignGoogle()

            is SignInIntent.CanceledSignKakao -> handleCanceledSignKakao()

            is SignInIntent.FailedSignGoogle -> handleFailedSignGoogle()

            is SignInIntent.FailedSignKakao -> handleFailedSignKakao()

            is SignInIntent.CompleteSignGoogle -> handleCompleteSignGoogle(intent)

            is SignInIntent.CompleteSignKakao -> handleCompleteSignKakao(intent)

            is SignInIntent.FailedSyncData -> handleFailedSyncData()

            is SignInIntent.SuccessSyncData -> handleSuccessSyncData(intent)
        }
    }

    private fun handleFetchFcmToken(intent: SignInIntent.FetchFcmToken) {
        reduce {
            copy(
                fcmToken = intent.fcmToken,
            )
        }
    }

    private fun handleOnClickSignGoogle() {
        reduce {
            copy(isLoading = true)
        }

        postSideEffect(SignInSideEffect.GoogleSignIn)
    }

    private fun handleOnClickSignKakao() {
        reduce {
            copy(isLoading = true)
        }

        postSideEffect(SignInSideEffect.KakaoSignIn)
    }

    private fun handleOnClickPrivacyPolicy() {
        postSideEffect(SignInSideEffect.NavigateToPrivacyPolicy)
    }

    private fun handleOnClickTermsOfUse() {
        postSideEffect(SignInSideEffect.NavigateToTermsOfUse)
    }

    private fun handleCanceledSignGoogle() {
        canceledSignIn()
    }

    private fun handleCanceledSignKakao() {
        canceledSignIn()
    }

    private fun handleFailedSignGoogle() {
        failedSignIn()
    }

    private fun handleFailedSignKakao() {
        failedSignIn()
    }

    private suspend fun handleCompleteSignGoogle(intent: SignInIntent.CompleteSignGoogle) {
        googleSignIn(token = intent.token)
    }

    private suspend fun handleCompleteSignKakao(intent: SignInIntent.CompleteSignKakao) {
        kakaoSignIn(token = intent.accessToken)
    }

    private suspend fun handleFailedSyncData() {
        clearAllTokenUseCase()
        failedSignIn()
    }

    private suspend fun handleSuccessSyncData(intent: SignInIntent.SuccessSyncData) {
        reduce {
            copy(
                isLoading = false,
            )
        }

        postSideEffect(
            SignInSideEffect.NavigateToSignUp(
                nickname = intent.nickname,
            ),
        )
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

    private suspend fun googleSignIn(token: String) = launch(Dispatchers.IO) {
        val result = signingGoogleUseCase(
            token = token,
            fcmToken = currentState.fcmToken,
        ).getOrNull()

        if (result == null) {
            analyticsHelper.d(message = "result is null")
            failedSignIn()
            return@launch
        }

        if (result == false) {
            analyticsHelper.d(message = "result: false")
            failedSignIn()
            return@launch
        }

        successSign()
    }

    private suspend fun kakaoSignIn(token: String) = launch {
        val result = signingKakaoUseCase(
            token = token,
            fcmToken = currentState.fcmToken,
        ).getOrNull()

        if (result == null) {
            analyticsHelper.d(message = "result is null",)
            failedSignIn()
            return@launch
        }

        if (result == false) {
            analyticsHelper.d(message = "result: false")
            failedSignIn()
            return@launch
        }

        successSign()
    }

    private fun successSign() {
        postSideEffect(SignInSideEffect.SuccessSocialSign)
    }

}