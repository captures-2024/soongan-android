package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.core.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.members.SetGuestModeUseCase
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignViewModel
@Inject
constructor(
    private val getFcmUseCase: GetFcmUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val setGuestModeUseCase: SetGuestModeUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<SignViewModel.State, SignViewModel.Effect, SignViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    savedStateHandle = savedStateHandle,
) {

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
         * 카카오 로그인이 성공적으로 완료되었을 때 발생하는 인텐트
         *
         * @property accessToken 카카오로부터 받은 access 토큰
         * @property refreshToken 토큰 갱신을 위해 카카오로부터 받은 refresh 토큰
         */
        data class CompleteSignKakao(
            val accessToken: String,
            val refreshToken: String,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickGuestMode -> handleOnClickGuestMode()

            is Intent.OnClickSignKakao -> loadingLaunch { handleOnClickSignKakao() }

            is Intent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()

            is Intent.OnClickTermsOfUse -> handleOnClickTermsOfUse()

            is Intent.CompleteSignKakao -> launch { handleCompleteSignKakao(intent) }
        }
    }

    private fun handleOnClickGuestMode() {
        setGuestModeUseCase(isGuestMode = true)
    }

    private fun handleOnClickSignKakao() {
        postSideEffect(Effect.KakaoSignIn)
    }

    private fun handleOnClickPrivacyPolicy() {
        postSideEffect(Effect.NavigateToPrivacyPolicy)
    }

    private fun handleOnClickTermsOfUse() {
        postSideEffect(Effect.NavigateToTermsOfUse)
    }

    private suspend fun handleCompleteSignKakao(intent: Intent.CompleteSignKakao) {
        kakaoSignIn(token = intent.accessToken)
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
                }
            }

            else -> {
                analyticsHelper.d(message = "kakaoSignIn - result: $result")
            }
        }
    }
}