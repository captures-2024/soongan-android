package com.captures2024.soongan.feature.home.state

import com.captures2024.soongan.core.model.mock.GalleryImage

sealed class PhotoDetailModelState(
    open val model: GalleryImage
) {
    data object NonInit : PhotoDetailModelState(
        model = GalleryImage(
            id = -1,
            url = "",
            title = ""
        )
    )

    data class Init(
        override val model: GalleryImage
    ) : PhotoDetailModelState(
        model = model
    )
}