package com.captures2024.soongan.feature.home.state.home_gallery

import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter

internal data class HomeGalleryUIState(
    val isLoading: Boolean = false,
    val isShowBottomSheet: Boolean = false,
    val sortOrder: GalleryPhotoSortFilter = GalleryPhotoSortFilter.LIKES,
    val photoList: List<UserPost> = emptyList(),
) : UIState
