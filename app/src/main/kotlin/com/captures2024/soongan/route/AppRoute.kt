package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.AppRootViewModel
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.ui.AppRootScreen

@Composable
internal fun AppRoute(
    appRootViewModel: AppRootViewModel,
) {
    val uiState by appRootViewModel.state.collectAsStateWithLifecycle()

    SoonGanBackground {
        AppRootScreen(
            uiState = uiState,
        )
    }
}