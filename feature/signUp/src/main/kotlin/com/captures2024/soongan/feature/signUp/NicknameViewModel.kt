package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.IsAllowNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.RegisterNicknameUseCase
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameIntent
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameSideEffect
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class NicknameViewModel
@Inject
constructor(
    private val isAllowNicknameUseCase: IsAllowNicknameUseCase,
    private val registerNicknameUseCase: RegisterNicknameUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<NicknameUIState, NicknameSideEffect, NicknameIntent>(savedStateHandle) {
//    private val _uiState = MutableStateFlow<InputNickNameUIState>(InputNickNameUIState.Init)
//    val uiState: StateFlow<InputNickNameUIState>
//        get() = _uiState
//
//    fun restoreState() {
//        _uiState.value = InputNickNameUIState.Init
//    }
//
//    fun onChangedValue(nickname: String) {
//        val currentState = _uiState.value
//
//        if (currentState is InputNickNameUIState.Loading) {
//            return
//        }
//
//        if (nickname.length > 10) {
//            return
//        }
//
//        _uiState.value = InputNickNameUIState.ValueChanged(nickname = nickname)
//    }
//
//    fun duplicationCheck() {
//        when (val currentState = _uiState.value) {
//            is InputNickNameUIState.Init,
//            is InputNickNameUIState.Loading,
//            is InputNickNameUIState.Error -> return
//
//            else -> viewModelScope.launch {
//                _uiState.emit(InputNickNameUIState.Loading(currentState.nickname))
//
//                val isAllow = isAllowNicknameUseCase(currentState.nickname).getOrNull() ?: return@launch _uiState.emit(InputNickNameUIState.Error(currentState.nickname))
//
//                if (!isAllow) {
//                    _uiState.emit(InputNickNameUIState.Error(currentState.nickname))
//                    return@launch
//                }
//
//                registerNickname(currentState.nickname)
//            }
//        }
//    }
//
//    private suspend fun registerNickname(nickname: String) {
//        val isRegister = registerNicknameUseCase(nickname).getOrNull() ?: return _uiState.emit(InputNickNameUIState.Error(nickname))
//
//        when (isRegister.result) {
//            true -> _uiState.emit(InputNickNameUIState.Success(nickname))
//            false -> _uiState.emit(InputNickNameUIState.Error(nickname))
//        }
//    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): NicknameUIState = NicknameUIState()

    override fun handleClientException(throwable: Throwable) {
//        Timber.tag(TAG).e(throwable)
    }

    override suspend fun handleIntent(intent: NicknameIntent) {
        when (intent) {
            is NicknameIntent.OnClickBack -> onClickBack()

            is NicknameIntent.OnClickConfirm -> onClickConfirm()

            is NicknameIntent.OnValueChanged -> onValueChanged(intent.nickname)

            is NicknameIntent.RegisterNickname -> registerNickname()
        }
    }

    private fun onClickBack() {
        postSideEffect(NicknameSideEffect.NavigateToBack)
    }

    private fun onClickConfirm() {
        reduce {
            copy(
                isLoading = true
            )
        }

        launch {
            val isAllow = isAllowNicknameUseCase(currentState.nickname).getOrNull()

            if (isAllow == null) {
//                Timber.tag(TAG).d("isAllow is null")
                reduce {
                    copy(isLoading = false)
                }
                return@launch
            }

            when (isAllow) {
                true -> intent(NicknameIntent.RegisterNickname)

                false -> {
//                    Timber.tag(TAG).d("isAllow is false")
                    reduce {
                        copy(
                            isLoading = false,
                            isDuplicatedNickname = true
                        )
                    }
                }
            }
        }
    }

    private fun onValueChanged(newValue: String) {
        reduce {
            copy(
                nickname = newValue,
                isDuplicatedNickname = false
            )
        }
    }

    private fun registerNickname() {
        launch {
            if (currentState.isDuplicatedNickname) {
//                Timber.tag(TAG).d("${currentState.nickname} is duplicated")
                reduce {
                    copy(
                        isLoading = false,
                        isDuplicatedNickname = true
                    )
                }
                return@launch
            }

            val currentNickname = currentState.nickname

            val isRegister = registerNicknameUseCase(currentNickname).getOrNull()

            if (isRegister == null) {
//                Timber.tag(TAG).d("isRegister is null")
                reduce {
                    copy(
                        isLoading = false,
                    )
                }
                return@launch
            }

            when (isRegister.result) {
                true -> postSideEffect(NicknameSideEffect.NavigateToBirthDate)

                false -> {
//                    Timber.tag(TAG).d("${currentState.nickname} post failed")
                    reduce {
                        copy(
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "NicknameVM"
    }
}