package com.captures2024.soongan.feature.home.state

sealed class PhotoDetailUIState(
    open val menuModalState: PhotoDetailModalState,
    open val commentModalState: PhotoDetailModalState,
    open val modelState: PhotoDetailModelState
) {

    data object Init : PhotoDetailUIState(
        menuModalState = PhotoDetailModalState.Close,
        commentModalState = PhotoDetailModalState.Close,
        modelState = PhotoDetailModelState.NonInit
    )

    data class LoadImage(
        override val menuModalState: PhotoDetailModalState,
        override val commentModalState: PhotoDetailModalState,
        override val modelState: PhotoDetailModelState.Init
    ) : PhotoDetailUIState(
        menuModalState = menuModalState,
        commentModalState = commentModalState,
        modelState = modelState
    )

}