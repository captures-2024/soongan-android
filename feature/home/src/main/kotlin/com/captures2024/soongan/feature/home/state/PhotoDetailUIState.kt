package com.captures2024.soongan.feature.home.state

sealed class PhotoDetailUIState(
    open val modalState: PhotoDetailModalState,
    open val modelState: PhotoDetailModelState
) {

    data object Init : PhotoDetailUIState(
        modalState = PhotoDetailModalState.Close,
        modelState = PhotoDetailModelState.NonInit
    )

    data class LoadImage(
        override val modalState: PhotoDetailModalState,
        override val modelState: PhotoDetailModelState.Init
    ) : PhotoDetailUIState(
        modalState = modalState,
        modelState = modelState
    )

}