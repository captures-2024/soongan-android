package com.captures2024.soongan.feature.signIn

import android.util.Log
import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.model.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject
constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<SignInState>(SignInState.Init)
    val uiState: StateFlow<SignInState>
        get() = _uiState

    fun onClickGoogleSignIn(
        active: () -> Unit
    ) {
        val currentState = _uiState.value

        if (isNotPossibleOperationState(currentState)) {
            return
        }

        _uiState.value = SignInState.Loading

        active()
    }

    suspend fun finishGoogleSignIn(signInResult: SignInResult) {
        when (signInResult.data) {
            null -> {
                Log.d(TAG, "errorMessage = ${signInResult.errorMessage}")
                _uiState.emit(SignInState.ErrorSignIn)
            }
            else -> {

            }
        }
    }

    fun onClickKakaoSignIn() {
        val currentState = _uiState.value

        if (isNotPossibleOperationState(currentState)) {
            return
        }

        _uiState.value = SignInState.Loading
    }

    private fun isNotPossibleOperationState(currentState: SignInState): Boolean = when (currentState) {
        is SignInState.Init, SignInState.ErrorSignIn -> false
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