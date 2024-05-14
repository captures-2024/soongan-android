package com.captures2024.soongan.feature.signIn

import android.util.Log
import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.model.SignInResult
import com.captures2024.soongan.feature.signIn.SignInState.ErrorSignIn
import com.captures2024.soongan.feature.signIn.SignInState.Home
import com.captures2024.soongan.feature.signIn.SignInState.Init
import com.captures2024.soongan.feature.signIn.SignInState.Loading
import com.captures2024.soongan.feature.signIn.SignInState.SignUp
import com.captures2024.soongan.feature.signIn.SignInState.SuccessSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<SignInState>(Init)
    val uiState: StateFlow<SignInState>
        get() = _uiState

    fun onClickSignIn(
        activeSocialSignIn: () -> Unit
    ) {
        val currentState = _uiState.value

        if (isNotPossibleOperationState(currentState)) {
            return
        }

        _uiState.value = Loading

        activeSocialSignIn()
    }

    suspend fun finishAppleSignIn(signInResult: SignInResult) {
        when (signInResult.data) {
            null -> {
                Log.d(TAG, "errorMessage = ${signInResult.errorMessage}")
                _uiState.emit(ErrorSignIn)
            }
            else -> {
                // TODO Success Logic
            }
        }
    }

    suspend fun finishGoogleSignIn(signInResult: SignInResult) {
        when (signInResult.data) {
            null -> {
                Log.d(TAG, "errorMessage = ${signInResult.errorMessage}")
                _uiState.emit(ErrorSignIn)
            }
            else -> {
                // TODO Success Logic
            }
        }
    }

    suspend fun finishKakaoSignIn(signInResult: SignInResult) {
        when (signInResult.data) {
            null -> {
                Log.d(TAG, "errorMessage = ${signInResult.errorMessage}")
                _uiState.emit(ErrorSignIn)
            }
            else -> {
                // TODO Success Logic
            }
        }
    }

    /**
     * 현재 상태가 로그인을 기능을 작동시킬 수 있는 상태인지 판정
     *
     * 만약 loading, 또는 다른 화면으로 이동 중인 경우 작동 실패
     *
     * @return if 로그인 작동이 불가능한 경우 true else false
     *
     * @param currentState SignInState 타입의 상태
     * @see SignInState
     **/
    private fun isNotPossibleOperationState(currentState: SignInState): Boolean = when (currentState) {
        is Init, ErrorSignIn -> false
        else -> true
    }

    companion object {
        private const val TAG = "SignInVM"
    }
}

/**
 * State 설명
 * @property Init 초기 상태
 * @property Loading 데이터 통신이 있을 경우 대기 상태로 변환된 상태
 * @property ErrorSignIn 로그인이 실패한 상태
 * @property SuccessSignIn 로그인이 성공한 상태
 * @property Home 홈으로 이동 시키는 경우의 상태
 * @property SignUp 회원가입으로 이동 시키는 경우의 상태
 **/
sealed interface SignInState {
    data object Init : SignInState

    data object Loading : SignInState
    data object ErrorSignIn : SignInState

    data object SuccessSignIn : SignInState

    data object Home : SignInState

    data object SignUp : SignInState
}