package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NicknameViewModel
@Inject
constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<InputNickNameUIState>(InputNickNameUIState.Init)
    val uiState: StateFlow<InputNickNameUIState>
        get() = _uiState


    fun onChangedValue(nickname: String) {
        val currentState = _uiState.value

        if (currentState is InputNickNameUIState.Loading) {
            return
        }

        if (nickname.length > 10) {
            return
        }

        _uiState.value = InputNickNameUIState.ValueChanged(nickname = nickname)
    }

    fun duplicationCheck() {
        _uiState.value = InputNickNameUIState.Success("testNick")

        // TODO duplication RESTFul API Connection
//
//        val currentState = _uiState.value
//        when (currentState) {
//            is InputNickNameUIState.Init,
//            is InputNickNameUIState.Loading,
//            is InputNickNameUIState.Error -> {
//                return
//            }
//            else -> Unit
//        }
//
//
    }

    companion object {
        private const val TAG = "NicknameVM"
    }
}


sealed class InputNickNameUIState(
    open val nickname: String
) {
    data object Init : InputNickNameUIState(nickname = "")

    data class Loading(
        override val nickname: String
    ) : InputNickNameUIState(nickname = nickname)

    data class ValueChanged(
        override val nickname: String
    ) : InputNickNameUIState(nickname = nickname)

    data class Error(
        override val nickname: String
    ) : InputNickNameUIState(nickname = nickname)

    data class Success(
        override val nickname: String
    ) : InputNickNameUIState(nickname = nickname)

}