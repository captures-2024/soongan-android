package com.captures2024.soongan.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.core.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.core.viewmodel.effect.SignSideEffect
import com.captures2024.soongan.core.viewmodel.intent.SignIntent
import com.captures2024.soongan.core.viewmodel.state.SignUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SignViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val clearAllTokenUseCase: ClearAllTokenUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignUIState, SignSideEffect, SignIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SignUIState = SignUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override suspend fun handleIntent(intent: SignIntent) {
        when (intent) {
            is SignIntent.FetchFcmToken -> handleFetchFcmToken(intent)

            is SignIntent.OnClickGuestMode -> handleOnClickGuestMode()

            is SignIntent.OnClickSignGoogle -> handleOnClickSignGoogle()

            is SignIntent.OnClickSignKakao -> handleOnClickSignKakao()

            is SignIntent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()

            is SignIntent.OnClickTermsOfUse -> handleOnClickTermsOfUse()

            is SignIntent.CanceledSignGoogle -> handleCanceledSignGoogle()

            is SignIntent.CanceledSignKakao -> handleCanceledSignKakao()

            is SignIntent.FailedSignGoogle -> handleFailedSignGoogle()

            is SignIntent.FailedSignKakao -> handleFailedSignKakao()

            is SignIntent.CompleteSignGoogle -> handleCompleteSignGoogle(intent)

            is SignIntent.CompleteSignKakao -> handleCompleteSignKakao(intent)

            is SignIntent.FailedSyncData -> handleFailedSyncData()

            is SignIntent.SuccessSyncData -> handleSuccessSyncData(intent)

            is SignIntent.SuccessPathBirth -> handleSuccessPathBirth(intent)
        }
    }

    private fun handleFetchFcmToken(intent: SignIntent.FetchFcmToken) {
        reduce {
            copy(
                fcmToken = intent.fcmToken,
            )
        }
    }

    private fun handleOnClickGuestMode() {
        postSideEffect(SignSideEffect.NavigateToMain)
    }

    private fun handleOnClickSignGoogle() {
        reduce {
            copy(isLoading = true)
        }

        postSideEffect(SignSideEffect.GoogleSignIn)
    }

    private fun handleOnClickSignKakao() {
        reduce {
            copy(isLoading = true)
        }

        postSideEffect(SignSideEffect.KakaoSignIn)
    }

    private fun handleOnClickPrivacyPolicy() {
        postSideEffect(SignSideEffect.NavigateToPrivacyPolicy)
    }

    private fun handleOnClickTermsOfUse() {
        postSideEffect(SignSideEffect.NavigateToTermsOfUse)
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

    private suspend fun handleCompleteSignGoogle(intent: SignIntent.CompleteSignGoogle) {
        googleSignIn(token = intent.token)
    }

    private suspend fun handleCompleteSignKakao(intent: SignIntent.CompleteSignKakao) {
        kakaoSignIn(token = intent.accessToken)
    }

    private suspend fun handleFailedSyncData() {
        clearAllTokenUseCase()
        failedSignIn()
    }

    private suspend fun handleSuccessSyncData(intent: SignIntent.SuccessSyncData) {
        reduce {
            copy(
                isLoading = false,
            )
        }

        postSideEffect(
            SignSideEffect.NavigateToSignUp(
                nickname = intent.nickname,
            ),
        )
    }

    private suspend fun handleSuccessPathBirth(intent: SignIntent.SuccessPathBirth) {
        postSideEffect(
            SignSideEffect.PatchInfo(
                nickname = intent.nickname,
                birthYear = intent.birthYear,
            )
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
        postSideEffect(SignSideEffect.SuccessSocialSign)
    }
}