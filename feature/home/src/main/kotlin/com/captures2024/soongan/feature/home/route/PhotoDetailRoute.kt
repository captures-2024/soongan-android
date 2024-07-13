package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.home.PhotoDetailViewModel
import com.captures2024.soongan.feature.home.samplePhotos
import com.captures2024.soongan.feature.home.state.PhotoDetailModalState
import com.captures2024.soongan.feature.home.ui.PhotoDetailBottomSheetDialog
import com.captures2024.soongan.feature.home.ui.PhotoDetailControlScreen
import com.captures2024.soongan.feature.home.ui.PhotoDetailScreen

@Composable
internal fun PhotoDetailRoute(
    photoDetailViewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val uiState = photoDetailViewModel.uiState.collectAsStateWithLifecycle().value

    PhotoDetailControlScreen(url = samplePhotos[20].url)
//    PhotoDetailScreen(item = samplePhotos[20])
//    PhotoDetailBottomSheetDialog(modalState = uiState.modalState)
}