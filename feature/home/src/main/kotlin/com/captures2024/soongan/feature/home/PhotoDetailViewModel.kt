package com.captures2024.soongan.feature.home

import androidx.lifecycle.ViewModel
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.state.PhotoDetailModalState
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
            menuModalState = currentState.menuModalState,
            commentModalState = currentState.commentModalState,
            modelState = PhotoDetailModelState.Init((samplePhotos[20] as UserPost.PhotoPost))
        )
    }

    fun openModal(isComment: Boolean) {
        val currentState = _uiState.value

        if (currentState.modelState is PhotoDetailModelState.NonInit) {
            return
        }

        if (currentState.menuModalState is PhotoDetailModalState.Open || currentState.commentModalState is PhotoDetailModalState.Open) {
            // menu 또는 comment modal이 열린 상태
            return
        }

        _uiState.value = PhotoDetailUIState.LoadImage(
            menuModalState = when (isComment) {
                true -> currentState.menuModalState
                false -> PhotoDetailModalState.Open()
            },
            commentModalState = when (isComment) {
                true -> PhotoDetailModalState.Open()
                false -> currentState.commentModalState
            },
            modelState = PhotoDetailModelState.Init((samplePhotos[20] as UserPost.PhotoPost))
        )
    }

    fun closeModal(isComment: Boolean) {
        val currentState = _uiState.value

        if (currentState.modelState is PhotoDetailModelState.NonInit) {
            return
        }

        if (currentState.menuModalState !is PhotoDetailModalState.Open && currentState.commentModalState !is PhotoDetailModalState.Open) {
            // Modal이 아무것도 open 되지 않은 상태
            return
        }

        _uiState.value = PhotoDetailUIState.LoadImage(
            menuModalState = when (isComment) {
                true -> currentState.menuModalState
                false -> PhotoDetailModalState.Close
            },
            commentModalState = when (isComment) {
                true -> PhotoDetailModalState.Close
                false -> currentState.commentModalState
            },
            modelState = PhotoDetailModelState.Init((samplePhotos[20] as UserPost.PhotoPost))
        )
    }

    fun onCommentValueChanged(comment: String) {
        val currentState = _uiState.value

        if (currentState.modelState is PhotoDetailModelState.NonInit) {
            return
        }

        if (currentState.commentModalState !is PhotoDetailModalState.Open) {
            return
        }

        _uiState.value = PhotoDetailUIState.LoadImage(
            menuModalState = currentState.menuModalState,
            commentModalState = PhotoDetailModalState.Open(comment),
            modelState = (currentState.modelState as PhotoDetailModelState.Init)
        )
    }

    companion object {
        private const val TAG = "PhotoDetailVM"
    }
}

