package com.captures2024.soongan.feature.home

import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoDetailControlViewModel
@Inject
constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow<PhotoDetailControlUIState>(PhotoDetailControlUIState.Init)
    val uiState : StateFlow<PhotoDetailControlUIState>
        get() = _uiState

    fun loadImage(photoUrl: String) {
        val currentState = _uiState.value

        if (currentState !is PhotoDetailControlUIState.Init) {
            return
        }

        _uiState.value = PhotoDetailControlUIState.LoadImage(photoUrl = (samplePhotos[20] as UserPost.PhotoPost).url)
    }

}

sealed class PhotoDetailControlUIState(
    open val photoUrl: String
) {
    data object Init : PhotoDetailControlUIState(photoUrl = "")
    data class LoadImage(
        override val photoUrl: String
    ) : PhotoDetailControlUIState(photoUrl = photoUrl)

}