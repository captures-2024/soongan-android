package com.captures2024.soongan.feature.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
@Inject
constructor(

) : ViewModel() {
    val uiState: StateFlow<WelcomeUiState> = flow<WelcomeUiState> {
        emit(WelcomeUiState.Success("테스트"))

    }.stateIn(
        scope = viewModelScope,
        initialValue = WelcomeUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    companion object {
        private const val TAG = "WelcomeVM"
    }
}

sealed interface WelcomeUiState {
    data object Loading : WelcomeUiState
    data class Success(
        val nickname: String
    ) : WelcomeUiState

    data object MoveHome : WelcomeUiState
}