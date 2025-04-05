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

        data object KakaoSignIn : Effect

        data class GoogleSignIn(
            val signInRequestCode: Int,
        ) : Effect

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
         * 사용자가 구글 로그인을 시도할 때 발생하는 인텐트
         */
        data object OnClickSignGoogle : Intent

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
            val accessToken: String?,
            val refreshToken: String?,
        ) : Intent

        data class CompleteSignGoogleResult(
            val token: String?,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickGuestMode -> handleOnClickGuestMode()
            is Intent.OnClickSignKakao -> loadingLaunch { handleOnClickSignKakao() }
            is Intent.OnClickSignGoogle -> handleOnClickSignGoogle()
            is Intent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()
            is Intent.OnClickTermsOfUse -> handleOnClickTermsOfUse()
            is Intent.CompleteSignKakao -> launch { handleCompleteSignKakao(intent) }
            is Intent.CompleteSignGoogleResult -> loadingLaunch { handleCompleteSignGoogleResult(intent) }
        }
    }

    private fun handleOnClickGuestMode() {
        setGuestModeUseCase(isGuestMode = true)
    }

    private fun handleOnClickSignKakao() {
        postSideEffect(Effect.KakaoSignIn)
    }

    private fun handleOnClickSignGoogle() {
        postSideEffect(Effect.GoogleSignIn(SIGN_IN_REQUEST_CODE))
    }

    private fun handleOnClickPrivacyPolicy() {
        postSideEffect(Effect.NavigateToPrivacyPolicy)
    }

    private fun handleOnClickTermsOfUse() {
        postSideEffect(Effect.NavigateToTermsOfUse)
    }

    private suspend fun handleCompleteSignKakao(intent: Intent.CompleteSignKakao) {
        if (intent.accessToken == null) {
            return
        }

        kakaoSignIn(token = intent.accessToken)
    }

    private suspend fun handleCompleteSignGoogleResult(intent: Intent.CompleteSignGoogleResult) {
        val token = intent.token ?: return
        googleSignIn(token)
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

                postSideEffect(Effect.NavigateToSignUp(infoDto?.nickname))
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

                postSideEffect(Effect.NavigateToSignUp(infoDto?.nickname))
            }

            else -> {
                analyticsHelper.d { "googleSign - result: $result" }
            }
        }
    }

    companion object {
        private const val SIGN_IN_REQUEST_CODE = 158
    }
}
