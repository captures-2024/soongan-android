package com.captures2024.soongan.feature.home.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.viewmodel.home.HomeGalleryViewModel
import com.captures2024.soongan.core.viewmodel.home.HomeGalleryViewModel.Effect
import com.captures2024.soongan.core.viewmodel.home.HomeGalleryViewModel.Intent
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryBottomSheet
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (Long, NavOptions?) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    getHideTargetContentId: () -> Long,
    homeGalleryViewModel: HomeGalleryViewModel = hiltViewModel(),
) {
    val uiState by homeGalleryViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeGalleryViewModel.sideEffect.collect { effect ->
            when (effect) {
                is Effect.NavigateToHomePost -> navigateToPost(effect.postId, null)

                is Effect.NavigateToRegistrationPost -> navigateToRegistrationPost()
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        val targetId = getHideTargetContentId()

        if (targetId != -1L) {
            homeGalleryViewModel.intent(Intent.HidePost(targetId))
        }
    }

    HomeGalleryScreen(
        uiState = uiState,
        onBackPressed = navigateToBack,
        onRefresh = { homeGalleryViewModel.intent(Intent.RefreshGallery) },
        onLoadNextPage = { homeGalleryViewModel.intent(Intent.LoadNextPage) },
        onClickPost = { homeGalleryViewModel.intent(Intent.OnClickPost(it)) },
        onClickFilter = { homeGalleryViewModel.intent(Intent.OnClickFilter) },
        onClickRegistrationText = { homeGalleryViewModel.intent(Intent.OnClickRegistrationText) },
    )

    if (uiState.isOpenFilterBottomSheet) {
        HomeGalleryBottomSheet(
            uiState = uiState,
            onDismissRequest = { homeGalleryViewModel.intent(Intent.OnFilterDismissRequest) },
            onClickItem = { homeGalleryViewModel.intent(Intent.OnClickSortFilter(it)) },
        )
    }
}
