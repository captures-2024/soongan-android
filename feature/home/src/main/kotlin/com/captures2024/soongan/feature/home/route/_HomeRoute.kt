package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.feature.home.HomeViewModel
import com.captures2024.soongan.feature.home.ui.home._HomeScreen

@Composable
internal fun _HomeRoute(
    navigateToHomeGallery: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    _HomeScreen()
}