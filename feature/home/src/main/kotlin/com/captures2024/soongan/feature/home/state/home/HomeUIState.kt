package com.captures2024.soongan.feature.home.state.home

import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter

internal data class HomeUIState(
    val isLoading: Boolean = false,
) : UIState
