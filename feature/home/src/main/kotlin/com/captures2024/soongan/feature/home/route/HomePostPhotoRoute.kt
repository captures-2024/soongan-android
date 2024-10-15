package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.home.HomePostPhotoViewModel
import com.captures2024.soongan.feature.home.ui.postphoto.HomePostPhotoScreen

@Composable
internal fun HomePostPhotoRoute(
    navigateToBack: () -> Unit,
    homePostPhotoViewModel: HomePostPhotoViewModel = hiltViewModel()
) {
    val uiState by homePostPhotoViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homePostPhotoViewModel.sideEffect.collect {

        }
    }

    HomePostPhotoScreen(
        uiState = uiState,
        onBackPressed = navigateToBack
    )
}