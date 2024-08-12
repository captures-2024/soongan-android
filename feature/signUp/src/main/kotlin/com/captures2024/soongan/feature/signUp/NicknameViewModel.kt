package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captures2024.soongan.core.domain.usecase.members.IsAllowNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NicknameViewModel
@Inject
constructor(
    private val isAllowNicknameUseCase: IsAllowNicknameUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<InputNickNameUIState>(InputNickNameUIState.Init)
    val uiState: StateFlow<InputNickNameUIState>
        get() = _uiState

    fun restoreState() {
        _uiState.value = InputNickNameUIState.Init
    }

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
        when (val currentState = _uiState.value) {
            is InputNickNameUIState.Init,
            is InputNickNameUIState.Loading,
            is InputNickNameUIState.Error -> return
            else -> viewModelScope.launch {
                _uiState.emit(InputNickNameUIState.Loading(currentState.nickname))

                val isAllow = isAllowNicknameUseCase(currentState.nickname).getOrNull() ?: return@launch _uiState.emit(InputNickNameUIState.Error(currentState.nickname))

                when (isAllow) {
                    true -> {
                        // TODO 회원 정보 수정
                        _uiState.emit(InputNickNameUIState.Success(currentState.nickname))
                    }
                    false -> _uiState.emit(InputNickNameUIState.Error(currentState.nickname))
                }
            }
        }
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