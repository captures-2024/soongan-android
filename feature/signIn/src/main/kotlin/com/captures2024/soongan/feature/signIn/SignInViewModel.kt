package com.captures2024.soongan.feature.signIn

import androidx.lifecycle.ViewModel
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

    fun onClickGoogleSignIn() {
        val currentState = _uiState.value

        if (currentState !is SignInState.Init) {
            return
        }

        _uiState.value = SignInState.Loading
    }

    fun onClickKakaoSignIn() {
        val currentState = _uiState.value

        if (currentState !is SignInState.Init) {
            return
        }

        _uiState.value = SignInState.Loading
    }

    companion object {
        private const val TAG = "SignInVM"
    }
}

/**
 * State 설명
 * @property Init 초기 상태
 * @property Loading 데이터 통신이 있을 경우 대기 상태로 변환된 상태
 * @property SuccessSignIn 로그인이 성공한 상태
 * @property Home 홈으로 이동 시키는 경우의 상태
 * @property SignUp 회원가입으로 이동 시키는 경우의 상태
 **/
sealed interface SignInState {
    data object Init : SignInState

    data object Loading : SignInState

    data object SuccessSignIn : SignInState

    data object Home : SignInState

    data object SignUp : SignInState
}