package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.auth.SigningGoogleUseCase
import com.captures2024.soongan.core.domain.usecase.auth.SigningKakaoUseCase
import com.captures2024.soongan.core.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.core.domain.usecase.token.ClearAllTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SignViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getFcmUseCase: GetFcmUseCase,
    private val signingGoogleUseCase: SigningGoogleUseCase,
    private val signingKakaoUseCase: SigningKakaoUseCase,
    private val clearAllTokenUseCase: ClearAllTokenUseCase,
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


        data object GoogleSignIn : Effect

        data object KakaoSignIn : Effect

        data object SuccessSocialSign : Effect

        data object NavigateToTermsOfUse : Effect

        data object NavigateToPrivacyPolicy : Effect

        data class NavigateToSignUp(
            val nickname: String?,
        ) : Effect

        data object NavigateToMain : Effect

        data class PatchInfo(
            val nickname: String,
            val birthYear: Int,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        /**
         * 사용자가 게스트 모드로 진입하려고 할 때 발생하는 인텐트
         */
        data object OnClickGuestMode : Intent

        data object OnClickSignGoogle : Intent

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
         * 사용자가 구글 로그인 과정을 취소했을 때 발생하는 인텐트
         */
        data object CanceledSignGoogle : Intent

        /**
         * 사용자가 카카오 로그인 과정을 취소했을 때 발생하는 인텐트
         */
        data object CanceledSignKakao : Intent

        /**
         * 구글 로그인이 성공적으로 완료되었을 때 발생하는 인텐트
         *
         * @property token 구글로부터 받은 인증 토큰
         */
        data class CompleteSignGoogle(
            val token: String,
        ) : Intent

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
         * 구글 로그인 시도가 실패했을 때 발생하는 인텐트
         */
        data object FailedSignGoogle : Intent

        /**
         * 카카오 로그인 시도가 실패했을 때 발생하는 인텐트
         */
        data object FailedSignKakao : Intent

        /**
         * 사용자 데이터 동기화가 실패했을 때 발생하는 인텐트
         */
        data object FailedSyncData : Intent

        /**
         * 사용자 데이터 동기화가 성공적으로 완료되었을 때 발생하는 인텐트
         *
         * @property nickname 사용자의 닉네임 (nullable)
         * @property birthYear 사용자의 출생연도 (nullable)
         */
        data class SuccessSyncData(
            val nickname: String?,
            val birthYear: Int?,
        ) : Intent

        /**
         * 사용자의 닉네임과 출생연도 입력이 모두 완료되었을 때 발생하는 인텐트
         *
         * @property nickname 사용자의 닉네임
         * @property birthYear 사용자의 출생연도
         */
        data class SuccessPathBirth(
            val nickname: String,
            val birthYear: Int,
        ) : Intent
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
            is Intent.OnClickGuestMode -> handleOnClickGuestMode()

            is Intent.OnClickSignGoogle -> handleOnClickSignGoogle()

            is Intent.OnClickSignKakao -> handleOnClickSignKakao()

            is Intent.OnClickPrivacyPolicy -> handleOnClickPrivacyPolicy()

            is Intent.OnClickTermsOfUse -> handleOnClickTermsOfUse()

            is Intent.CanceledSignGoogle -> handleCanceledSignGoogle()

            is Intent.CanceledSignKakao -> handleCanceledSignKakao()

            is Intent.FailedSignGoogle -> handleFailedSignGoogle()

            is Intent.FailedSignKakao -> handleFailedSignKakao()

            is Intent.CompleteSignGoogle -> handleCompleteSignGoogle(intent)

            is Intent.CompleteSignKakao -> handleCompleteSignKakao(intent)

            is Intent.FailedSyncData -> handleFailedSyncData()

            is Intent.SuccessSyncData -> handleSuccessSyncData(intent)

            is Intent.SuccessPathBirth -> handleSuccessPathBirth(intent)
        }
    }

    private fun handleOnClickGuestMode() {
        postSideEffect(Effect.NavigateToMain)
    }

    private fun handleOnClickSignGoogle() {
        reduce {
            copy(isLoading = true)
        }

        postSideEffect(Effect.GoogleSignIn)
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

    private suspend fun handleCompleteSignGoogle(intent: Intent.CompleteSignGoogle) {
        googleSignIn(token = intent.token)
    }

    private suspend fun handleCompleteSignKakao(intent: Intent.CompleteSignKakao) {
        kakaoSignIn(token = intent.accessToken)
    }

    private suspend fun handleFailedSyncData() {
        clearAllTokenUseCase()
        failedSignIn()
    }

    private suspend fun handleSuccessSyncData(intent: Intent.SuccessSyncData) {
        reduce {
            copy(
                isLoading = false,
            )
        }

        postSideEffect(
            Effect.NavigateToSignUp(
                nickname = intent.nickname,
            ),
        )
    }

    private suspend fun handleSuccessPathBirth(intent: Intent.SuccessPathBirth) {
        postSideEffect(
            Effect.PatchInfo(
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
        val fcmToken = getFcmUseCase().getOrNull()

        if(fcmToken == null) {
            analyticsHelper.d(message = "fcm token is null")
            return@launch
        }

        val result = signingGoogleUseCase(
            token = token,
            fcmToken = fcmToken,
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
        val fcmToken = getFcmUseCase().getOrNull()

        if(fcmToken == null) {
            analyticsHelper.d(message = "fcm token is null")
            return@launch
        }

        val result = signingKakaoUseCase(
            token = token,
            fcmToken = fcmToken,
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

    private fun successSign() {
        postSideEffect(Effect.SuccessSocialSign)
    }
}