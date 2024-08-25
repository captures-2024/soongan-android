package com.captures2024.soongan.feature.signIn

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.SigningGoogleUseCase
import com.captures2024.soongan.core.domain.usecase.members.SigningKakaoUseCase
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.captures2024.soongan.feature.signIn.state.SignInSideEffect
import com.captures2024.soongan.feature.signIn.state.SignInUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignInUIState, SignInSideEffect, SignInIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SignInUIState = SignInUIState()

    override fun handleClientException(throwable: Throwable) {
        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.OnClickSignApple -> appleSignIn()

            is SignInIntent.OnClickSignGoogle -> googleSignIn()

            is SignInIntent.OnClickSignKakao -> kakaoSignIn()

            is SignInIntent.CanceledSignApple,
            is SignInIntent.CanceledSignGoogle,
            is SignInIntent.CanceledSignKakao -> canceledSignIn()

            is SignInIntent.FailedSignApple,
            is SignInIntent.FailedSignGoogle,
            is SignInIntent.FailedSignKakao -> failedSignIn()

            is SignInIntent.CompleteSignApple -> TODO()

            is SignInIntent.CompleteSignGoogle -> googleSignIn(token = intent.token)

            is SignInIntent.CompleteSignKakao -> TODO()

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

    private fun appleSignIn() {
        reduce {
            copy(isLoading = true)
        }
        postSideEffect(SignInSideEffect.AppleSignIn)
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
            Timber.tag(TAG).d("errorMessage = result is Null")
            intent(SignInIntent.FailedSignGoogle)
            return@launch
        }

        Timber.tag(TAG).d("result = $result")

        reduce {
            copy(isLoading = false)
        }
    }

    private fun kakaoSignIn() {
        reduce {
            copy(isLoading = true)
        }
        postSideEffect(SignInSideEffect.KakaoSignIn)
    }

    private fun fetchFcmToken(token: String) {
        reduce {
            copy(
                fcmToken = token
            )
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

    companion object {
        private const val TAG = "SignInVM"
    }
}