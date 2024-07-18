package com.captures2024.soongan.feature.home.state

import com.captures2024.soongan.core.model.UserPost

private val skeletonPosts by lazy {
    List(100) { UserPost.SkeletonPost(id = it) }
}

sealed class HomeGalleryListUIState(
    open val photoList: List<UserPost>
) {

    data object Init : HomeGalleryListUIState(
        photoList = skeletonPosts
    )

    data object Loading : HomeGalleryListUIState(
        photoList = skeletonPosts
    )

    data class LoadPost(
        override val photoList: List<UserPost>
    ) : HomeGalleryListUIState(
        photoList = photoList
    )

}
