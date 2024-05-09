package com.captures2024.soongan.feature.intro.viewmodel

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
        delay(1000)
        emit(IntroActivityUiState.Success)
    }.stateIn(
        scope = viewModelScope,
        initialValue = IntroActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

}