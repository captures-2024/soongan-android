package com.captures2024.soongan.feature.home.state

sealed class PhotoDetailUIState(
    open val modalState: PhotoDetailModalState
) {

    data object Init : PhotoDetailUIState(
        modalState = PhotoDetailModalState.Open
    )

}