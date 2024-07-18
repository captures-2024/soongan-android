package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.home.HomeGalleryListViewModel
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryListScreen
import timber.log.Timber

@Composable
internal fun HomeGalleryListRoute(
    navigateToBack: () -> Unit,
    navigateToPhotoDetail: (String, NavOptions?) -> Unit,
    homeGalleryListViewModel: HomeGalleryListViewModel = hiltViewModel()
) {
    val uiState = homeGalleryListViewModel.uiState.collectAsStateWithLifecycle().value

    Timber.tag("HomeGalleryListRoute").d("uiState = $uiState")

    HomeGalleryListScreen(
        uiState = uiState,
        onClickBack = navigateToBack,
        onClickItem = navigateToPhotoDetail
    )
}