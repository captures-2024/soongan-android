package com.captures2024.soongan.feature.home.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.designsystem.ui.R
import com.captures2024.soongan.core.designsystem.ui.util.extension.sgBottomBarPadding
import com.captures2024.soongan.core.viewmodel.home.HomeViewModel
import com.captures2024.soongan.feature.home.ui.home.HomeScreen
import com.captures2024.soongan.feature.home.ui.home.HomeScreenBottomSheet

@Composable
internal fun HomeRoute(
    navigateToRegistrationPost: () -> Unit,
    navigateToPost: (Long, NavOptions?) -> Unit,
    navigateToGallery: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.state.collectAsStateWithLifecycle()

    val modifier = Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(id = R.drawable.background_home_gallery),
            contentScale = ContentScale.FillBounds,
            alpha = 0.8f,
        )
        .sgBottomBarPadding()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeViewModel.Effect.NavigateToRegistrationPost -> navigateToRegistrationPost()

                is HomeViewModel.Effect.NavigateToHomePost -> navigateToPost(effect.postInfo.postId, null)

                is HomeViewModel.Effect.NavigateToHomeGallery -> navigateToGallery()
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        homeViewModel.intent(HomeViewModel.Intent.Init)
    }

    HomeScreen(
        uiState = uiState,
        modifier = modifier,
        onClickPlus = { homeViewModel.intent(HomeViewModel.Intent.OnClickPlus) },
        onClickPost = { homeViewModel.intent(HomeViewModel.Intent.OnClickPost(it)) },
        onClickInfo = { homeViewModel.intent(HomeViewModel.Intent.OnClickInfo) },
        onClickRightArrow = { homeViewModel.intent(HomeViewModel.Intent.OnClickRightArrow) },
    )

    if (uiState.isOpenBottomSheet) {
        HomeScreenBottomSheet { homeViewModel.intent(HomeViewModel.Intent.OnCloseBottomSheet) }
    }
}
