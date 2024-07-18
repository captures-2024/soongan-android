package com.captures2024.soongan.feature.home.state

import com.captures2024.soongan.core.model.UserPost

sealed class PhotoDetailModelState(
    open val model: UserPost.PhotoPost
) {
    data object NonInit : PhotoDetailModelState(
        model = UserPost.PhotoPost(
            id = -1,
            url = "",
            title = ""
        )
    )

    data class Init(
        override val model: UserPost.PhotoPost
    ) : PhotoDetailModelState(
        model = model
    )
}