package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.home.PhotoDetailControlViewModel
import com.captures2024.soongan.feature.home.samplePhotos
import com.captures2024.soongan.feature.home.ui.photo.PhotoDetailControlScreen

@Composable
internal fun PhotoDetailControlRoute(
    photoUrl: String,
    photoDetailControlViewModel: PhotoDetailControlViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {
    val uiState = photoDetailControlViewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = true) {
        photoDetailControlViewModel.loadImage(photoUrl = photoUrl)
    }

    PhotoDetailControlScreen(
        uiState = uiState,
        onClickBack = onClickBack
    )
}