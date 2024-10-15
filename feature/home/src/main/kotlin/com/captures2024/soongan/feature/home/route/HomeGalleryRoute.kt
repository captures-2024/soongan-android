package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.HomeGalleryViewModel
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGalleryIntent
import com.captures2024.soongan.feature.home.state.home_gallery.HomeGallerySideEffect
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryScreen

@Composable
internal fun HomeGalleryRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (UserPost.PhotoPost) -> Unit,
    homeGalleryViewModel: HomeGalleryViewModel = hiltViewModel()
) {
    val uiState by homeGalleryViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeGalleryViewModel.sideEffect.collect { effect ->
           when (effect) {
               is HomeGallerySideEffect.NavigateToHomePost -> navigateToPost(effect.post)


           }
        }
    }

    HomeGalleryScreen(
        uiState = uiState,
        onBackPressed = navigateToBack,
        onClickPost = { homeGalleryViewModel.intent(HomeGalleryIntent.OnClickPost(it)) },
        onClickFilter = { homeGalleryViewModel.intent(HomeGalleryIntent.OnClickFilter) },
        onClickSortFilter = { homeGalleryViewModel.intent(HomeGalleryIntent.OnClickSortFilter(it)) },
        onBottomModalDismissRequest = { homeGalleryViewModel.intent(HomeGalleryIntent.OnBottomModalDismissRequest) },
    )
}