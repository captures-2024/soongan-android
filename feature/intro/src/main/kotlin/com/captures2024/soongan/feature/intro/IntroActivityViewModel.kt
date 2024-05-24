package com.captures2024.soongan.feature.intro

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
class IntroActivityViewModel
@Inject
constructor(

) : ViewModel() {
    val uiState: StateFlow<IntroActivityUiState> = flow<IntroActivityUiState> {
        delay(100)
        emit(IntroActivityUiState.Success)
    }.stateIn(
        scope = viewModelScope,
        initialValue = IntroActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

}

/**
 * State 설명
 *
 * Intro Activity의 각종 UI theme을 설정하는 ViewModel
 *
 * @property Loading
 * @property Success
 **/
sealed interface IntroActivityUiState {
    data object Loading : IntroActivityUiState
    data object Success : IntroActivityUiState
}