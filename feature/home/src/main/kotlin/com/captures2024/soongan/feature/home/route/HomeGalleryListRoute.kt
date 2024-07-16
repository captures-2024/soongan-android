package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.home.HomeGalleryListViewModel
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryListScreen

@Composable
internal fun HomeGalleryListRoute(
    navigateToBack: () -> Unit,
    navigateToPhotoDetail: (String, NavOptions?) -> Unit,
    homeGalleryListViewModel: HomeGalleryListViewModel = hiltViewModel()
) {
    HomeGalleryListScreen(
        onClickBack = navigateToBack,
        onClickItem = navigateToPhotoDetail
    )
}