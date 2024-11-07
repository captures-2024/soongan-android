package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.home.HomeViewModel
import com.captures2024.soongan.feature.home.state.home.HomeIntent
import com.captures2024.soongan.feature.home.state.home.HomeSideEffect
import com.captures2024.soongan.feature.home.ui.HomeScreen

@Composable
internal fun HomeRoute(
    navigateToExhibition: () -> Unit,
    navigateToMyPost: (UserPost.PhotoPost) -> Unit,
    navigateToGallery: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeSideEffect.NavigateToHomeExhibition -> navigateToExhibition()

                is HomeSideEffect.NavigateToHomePost -> navigateToMyPost(effect.myPost)

                is HomeSideEffect.NavigateToHomeGallery -> navigateToGallery()
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        onClickPlus = {homeViewModel.intent(HomeIntent.OnClickPlus)},
        onClickMyPost = {homeViewModel.intent(HomeIntent.OnClickMyPost(it))},
        onToggleWeeklyDaily = {homeViewModel.intent(HomeIntent.OnToggleWeeklyDaily)},
        onClickInfo = {homeViewModel.intent(HomeIntent.OnClickInfo)},
        onClickRightArrow = {homeViewModel.intent(HomeIntent.OnClickRightArrow)},
    )
}