package com.captures2024.soongan.feature.home.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.viewmodel.home.HomeGalleryViewModel
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryBottomSheet
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (Int, NavOptions?) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    homeGalleryViewModel: HomeGalleryViewModel = hiltViewModel(),
) {
    val uiState by homeGalleryViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeGalleryViewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeGalleryViewModel.Effect.NavigateToHomePost -> navigateToPost(effect.postId, null)

                is HomeGalleryViewModel.Effect.NavigateToRegistrationPost -> navigateToRegistrationPost()
            }
        }
    }

    HomeGalleryScreen(
        uiState = uiState,
        onBackPressed = navigateToBack,
        onRefresh = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.RefreshGallery) },
        onLoadNextPage = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.LoadNextPage) },
        onClickPost = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.OnClickPost(it)) },
        onClickFilter = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.OnClickFilter) },
        onClickRegistrationText = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.OnClickRegistrationText) }
    )

    if (uiState.isShowBottomSheet) {
        HomeGalleryBottomSheet(
            uiState = uiState,
            onDismissRequest = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.OnBottomModalDismissRequest) },
            onClickItem = { homeGalleryViewModel.intent(HomeGalleryViewModel.Intent.OnClickSortFilter(it)) },
        )
    }
}