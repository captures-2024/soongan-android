package com.captures2024.soongan.core.model

sealed class UserPost(
    open val id: Int,
) {

    data class SkeletonPost(
        override val id: Int,
        val height: Int = (100..300).random(),
    ) : UserPost(id = id)

    data class PhotoPost(
        override val id: Int,
        val url: String,
        val title: String,
    ) : UserPost(id = id)
}
