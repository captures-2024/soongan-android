package com.captures2024.soongan.feature.main

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
class MainActivityViewModel
@Inject
constructor(

) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = flow<MainActivityUiState> {
        emit(MainActivityUiState.Success)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )


    companion object {
        private const val TAG = "MainActVM"
    }
}

/**
 * State 설명
 *
 * Main Activity의 각종 UI theme을 설정하는 ViewModel
 *
 * @property Loading
 * @property Success
 **/
sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data object Success : MainActivityUiState
}