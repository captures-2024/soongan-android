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
class IntroViewModel
@Inject
constructor(

) : ViewModel() {
    val introState: StateFlow<IntroState> = flow<IntroState> {
        delay(100)
        emit(IntroState.Sign)
    }.stateIn(
        scope = viewModelScope,
        initialValue = IntroState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    companion object {
        private const val TAG = "IntroVM"
    }
}