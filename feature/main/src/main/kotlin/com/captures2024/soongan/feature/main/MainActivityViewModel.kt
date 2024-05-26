package com.captures2024.soongan.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val _uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)
    val uiState: StateFlow<MainActivityUiState>
        get() = _uiState

    fun setUpGuestMode(isGuestMode: Boolean) {
        _uiState.value = MainActivityUiState.Success(isGuestMode = isGuestMode)
    }



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
    data class Success(
        val isGuestMode: Boolean,
    ) : MainActivityUiState
}