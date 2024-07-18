package com.captures2024.soongan.feature.home

import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.state.PhotoDetailModelState
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

    fun loadImage(photoId: String) {
        val currentState = _uiState.value

        if (currentState.modelState !is PhotoDetailModelState.NonInit) {
            return
        }

        _uiState.value = PhotoDetailUIState.LoadImage(
            modalState = currentState.modalState,
            // TODO
//            modelState = PhotoDetailModelState.Init(model = )
            modelState = PhotoDetailModelState.Init((samplePhotos[20] as UserPost.PhotoPost))
        )
    }

    companion object {
        private const val TAG = "PhotoDetailVM"
    }
}

