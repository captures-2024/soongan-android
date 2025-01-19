package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.core.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.members.SetGuestModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getFcmUseCase: GetFcmUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val setGuestModeUseCase: SetGuestModeUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignViewModel.State, SignViewModel.Effect, SignViewModel.Intent>(savedStateHandle) {

    data class State(
        val isLoading: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object KakaoSignIn : Effect

        data object NavigateToTermsOfUse : Effect

        data object NavigateToPrivacyPolicy : Effect

        data class NavigateToSignUp(
            val nickname: String?,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        /**
         * 사용자가 게스트 모드로 진입하려고 할 때 발생하는 인텐트
         */
        data object OnClickGuestMode : Intent

        /**
         * 사용자가 카카오 로그인을 시도할 때 발생하는 인텐트
         */
        data object OnClickSignKakao : Intent

        /**
         * 사용자가 이용약관을 확인하려고 할 때 발생하는 인텐트
         */
        data object OnClickTermsOfUse : Intent

        /**
         * 사용자가 개인정보 보호정책을 확인하려고 할 때 발생하는 인텐트
         */
        data object OnClickPrivacyPolicy : Intent

        /**
         * 사용자가 카카오 로그인 과정을 취소했을 때 발생하는 인텐트
         */
        data object CanceledSignKakao : Intent

        /**
         * 카카오 로그인이 성공적으로 완료되었을 때 발생하는 인텐트
         *
         * @property accessToken 카카오로부터 받은 access 토큰
         * @property refreshToken 토큰 갱신을 위해 카카오로부터 받은 refresh 토큰
         */
        data class CompleteSignKakao(
            val accessToken: String,
            val refreshToken: String,
        ) : Intent

        /**
         * 카카오 로그인 시도가 실패했을 때 발생하는 인텐트
         */
        data object FailedSignKakao : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickGuestMode -> launch { handleOnClickGuestMode() }

            is Intent.OnClickSignKakao -> handleOnClickSignKakao()

            is Intent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()

            is Intent.OnClickTermsOfUse -> handleOnClickTermsOfUse()

            is Intent.CanceledSignKakao -> handleCanceledSignKakao()

            is Intent.FailedSignKakao -> handleFailedSignKakao()

            is Intent.CompleteSignKakao -> launch { handleCompleteSignKakao(intent) }
        }
    }

    private suspend fun handleOnClickGuestMode() {
        setGuestModeUseCase(isGuestMode = true)
    }

    private fun handleOnClickSignKakao() {
        reduce {
            copy(isLoading = true)
        }

        postSideEffect(Effect.KakaoSignIn)
    }

    private fun handleOnClickPrivacyPolicy() {
        postSideEffect(Effect.NavigateToPrivacyPolicy)
    }

    private fun handleOnClickTermsOfUse() {
        postSideEffect(Effect.NavigateToTermsOfUse)
    }

    private fun handleCanceledSignKakao() {
        canceledSignIn()
    }

    private fun handleFailedSignKakao() {
        failedSignIn()
    }

    private suspend fun handleCompleteSignKakao(intent: Intent.CompleteSignKakao) {
        kakaoSignIn(token = intent.accessToken)
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

    private suspend fun kakaoSignIn(token: String) {
        val fcmToken = getFcmUseCase().getOrNull()

        if (fcmToken == null) {
            analyticsHelper.d(message = "fcm token is null")
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
                    analyticsHelper.d(message = "kakaoSignIn - infoDto is null")
                    failedSignIn()
                }
            }

            else -> {
                analyticsHelper.d(message = "kakaoSignIn - result: $result")
                failedSignIn()
            }
        }
    }
}