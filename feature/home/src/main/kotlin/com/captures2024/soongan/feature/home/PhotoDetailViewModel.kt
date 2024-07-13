package com.captures2024.soongan.feature.home

import androidx.lifecycle.ViewModel
import com.captures2024.soongan.feature.home.state.PhotoDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel
@Inject
constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<PhotoDetailUIState>(PhotoDetailUIState.Init)
    val uiState: StateFlow<PhotoDetailUIState>
        get() = _uiState

    companion object {
        private const val TAG = "PhotoDetailVM"
    }
}

