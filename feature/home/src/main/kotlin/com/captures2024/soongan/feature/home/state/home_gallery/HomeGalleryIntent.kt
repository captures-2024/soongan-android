package com.captures2024.soongan.feature.home.state.home_gallery

import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.feature.home.utils.PostOrderType

internal sealed interface HomeGalleryIntent : UIIntent {

    data object Init : HomeGalleryIntent

    data object RefreshGallery : HomeGalleryIntent

    data object LoadNextPage : HomeGalleryIntent

    data class OnClickPost(
        val postId: Int,
    ) : HomeGalleryIntent

    data object OnClickRegistrationText : HomeGalleryIntent

    data object OnClickFilter : HomeGalleryIntent

    data class OnClickSortFilter(
        val postOrderType: PostOrderType,
    ) : HomeGalleryIntent

    data object OnBottomModalDismissRequest : HomeGalleryIntent

}