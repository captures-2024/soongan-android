package com.captures2024.soongan.presentation.feature.main.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.captures2024.soongan.presentation.feature.main.component.screen.MainScreen
import com.captures2024.soongan.presentation.feature.main.navigation.rememberMainNavigationState
import com.captures2024.soongan.presentation.viewmodel.main.MainNotificationViewModel

@Composable
fun MainRoute(
    isGuestMode: Boolean,
    navController: NavHostController,
    mainNotificationViewModel: MainNotificationViewModel = hiltViewModel(),
) {
    val mainNavigationState = rememberMainNavigationState(
        isGuestMode = isGuestMode,
        navController = navController,
    )

    val state by mainNotificationViewModel.state.collectAsState()

    MainScreen(
        navigationState = mainNavigationState,
        state = state,
    )
}
