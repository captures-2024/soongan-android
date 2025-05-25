package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuNotificationSettingScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileNotificationSettingViewModel

@Composable
internal fun ProfileMenuNotificationSettingRoute(
    navigateToBack: () -> Unit,
    viewModel: ProfileNotificationSettingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileNotificationSettingViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    ProfileMenuNotificationSettingScreen(
        state = state.notificationSettingState,
        onClickBack = { viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnClickBack) },
        onSwitchNotificationSettingType = { viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnSwitchNotificationSettingType(it)) },
    )
}
