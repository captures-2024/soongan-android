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
//    private val _uiState = MutableStateFlow<SignInState>(Init)
//    val uiState: StateFlow<SignInState>
//        get() = _uiState
//
//    fun restoreInitState() {
//        _uiState.value = Init
//    }
//
//    fun onClickSignIn(
//        activeSocialSignIn: () -> Unit
//    ) {
//        val currentState = _uiState.value
//
//        if (isNotPossibleOperationState(currentState)) {
//            return
//        }
//
//        _uiState.value = Loading
//
//        activeSocialSignIn()
//    }
//
//    fun canceledSignIn() = viewModelScope.launch {
//        _uiState.emit(Init)
//    }
//
//    fun finishAppleSignIn(signInResult: SignInResult) = viewModelScope.launch {
//        when (signInResult.data) {
//            null -> {
//                Log.d(TAG, "errorMessage = ${signInResult.errorMessage}")
//                _uiState.emit(ErrorSignIn)
//            }
//            else -> {
//                // TODO Success Logic
//            }
//        }
//    }
//
//    fun finishGoogleSignIn(token: String?) = viewModelScope.launch {
//        when (token) {
//            null -> {
//                Timber.tag(TAG).d("errorMessage = token is Null")
//                _uiState.emit(Init)
//            }
//            else -> {
//                val result = signingGoogleUseCase(
//                    token = token,
//                    fcmToken = ""
//                ).getOrNull()
//
//                when (result) {
//                    null -> {
//                        Timber.tag(TAG).d("errorMessage = result is Null")
//                        _uiState.emit(Init)
//                    }
//                    true -> {
//                        _uiState.emit(SignUp)
//                    }
//                    false -> {
//                        Timber.tag(TAG).d("errorMessage = result is false")
//                        _uiState.emit(Init)
//                    }
//                }
//            }
//        }
//    }
//
//    fun finishKakaoSignIn(signInResult: SignInResult) = viewModelScope.launch {
//        when (signInResult.data) {
//            null -> {
//                Log.d(TAG, "errorMessage = ${signInResult.errorMessage}")
//                _uiState.emit(ErrorSignIn)
//            }
//            else -> {
//                // TODO Success Logic
//            }
//        }
//    }
//
//    fun onSuccessKakao(
//        accessToken: String?,
//        refreshToken: String?
//    ) = viewModelScope.launch {
//        if (accessToken == null || refreshToken == null) {
//            Timber.tag(TAG).d("errorMessage = accessToken OR refreshToken is null")
//            _uiState.emit(SignUp)
//            return@launch
//        }
//
//        val result = signingKakaoUseCase(token = accessToken).getOrNull()
//
//        when (result) {
//            null -> {
//                Timber.tag(TAG).d("errorMessage = result is Null")
//                _uiState.emit(Init)
//            }
//            true -> {
//                _uiState.emit(SignUp)
//            }
//            false -> {
//                Timber.tag(TAG).d("errorMessage = result is false")
//                _uiState.emit(Init)
//            }
//        }
//    }
//
//    fun onFailureKakao(
//        error: Throwable?
//    ) = viewModelScope.launch {
//        Timber.tag(TAG).d("errorMessage = $error")
//        _uiState.emit(SignUp)
//    }
//
//    /**
//     * 현재 상태가 로그인을 기능을 작동시킬 수 있는 상태인지 판정
//     *
//     * 만약 loading, 또는 다른 화면으로 이동 중인 경우 작동 실패
//     *
//     * @return if 로그인 작동이 불가능한 경우 true else false
//     *
//     * @param currentState SignInState 타입의 상태
//     * @see SignInState
//     **/
//    private fun isNotPossibleOperationState(currentState: SignInState): Boolean = when (currentState) {
//        is Init, ErrorSignIn -> false
//        else -> true
//    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SignInUIState = SignInUIState()

    override fun handleClientException(throwable: Throwable) {
        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.OnClickSignApple -> appleSignIn()

            is SignInIntent.OnClickSignGoogle -> googleSignIn()

            is SignInIntent.OnClickSignKakao -> kakaoSignIn()

            is SignInIntent.CanceledSignApple -> TODO()

            is SignInIntent.CanceledSignGoogle -> TODO()

            is SignInIntent.CanceledSignKakao -> TODO()

            is SignInIntent.FailedSignApple,
            is SignInIntent.FailedSignGoogle,
            is SignInIntent.FailedSignKakao -> failedSignIn()

            is SignInIntent.CompleteSignApple -> TODO()

            is SignInIntent.CompleteSignGoogle -> googleSignIn(token = intent.token)

            is SignInIntent.CompleteSignKakao -> TODO()

            is SignInIntent.FetchFCMToken -> fetchFcmToken(token = intent.token)

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

    companion object {
        private const val TAG = "SignInVM"
    }
}