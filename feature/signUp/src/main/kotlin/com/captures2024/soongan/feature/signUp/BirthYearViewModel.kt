package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BirthYearViewModel
@Inject
constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<InputBirthYearUIState>(InputBirthYearUIState.Init)
    val uiState: StateFlow<InputBirthYearUIState>
        get() = _uiState


    fun initNickname(nickname: String) {
        val currentState = _uiState.value

        if (currentState !is InputBirthYearUIState.Init) {
            return
        }

        _uiState.value = InputBirthYearUIState.ValueChanged(
            nickname = nickname,
            birthYear = ""
        )
    }

    fun onValueChange(birthYear: String) {
        val currentState = _uiState.value

        when (currentState) {
            is InputBirthYearUIState.Init,
            is InputBirthYearUIState.Loading,
            is InputBirthYearUIState.Success -> return
            else -> Unit
        }

        _uiState.value = InputBirthYearUIState.ValueChanged(
            nickname = currentState.nickname,
            birthYear = birthYear
        )
    }

    companion object {
        private const val TAG = "BirthYearVM"
    }
}

sealed class InputBirthYearUIState(
    open val nickname: String,
    open val birthYear: String
) {
    data object Init : InputBirthYearUIState(
        nickname = "",
        birthYear = ""
    )

    data class Loading(
        override val nickname: String,
        override val birthYear: String
    ) : InputBirthYearUIState(
        nickname = nickname,
        birthYear = birthYear
    )

    data class ValueChanged(
        override val nickname: String,
        override val birthYear: String
    ) : InputBirthYearUIState(
        nickname = nickname,
        birthYear = birthYear
    )

    data class Error(
        override val nickname: String,
        override val birthYear: String
    ) : InputBirthYearUIState(
        nickname = nickname,
        birthYear = birthYear
    )

    data class Success(
        override val nickname: String,
        override val birthYear: String
    ) : InputBirthYearUIState(
        nickname = nickname,
        birthYear = birthYear
    )

}