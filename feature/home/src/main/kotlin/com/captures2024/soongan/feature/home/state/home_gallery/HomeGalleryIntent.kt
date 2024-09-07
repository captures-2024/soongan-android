package com.captures2024.soongan.feature.home.state.home_gallery

import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter

internal sealed interface HomeGalleryIntent : UIIntent {

    data class OnClickPost(
        val post: UserPost.PhotoPost
    ) : HomeGalleryIntent

    data object OnClickFilter : HomeGalleryIntent

    data class OnClickSortFilter(
        val selectedSortFilter: GalleryPhotoSortFilter
    ) : HomeGalleryIntent

    data object OnBottomModalDismissRequest : HomeGalleryIntent

}