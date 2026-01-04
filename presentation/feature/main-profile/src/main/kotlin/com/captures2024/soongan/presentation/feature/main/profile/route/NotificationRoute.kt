package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.presentation.feature.main.profile.component.notification.NotificationScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileNotificationViewModel

@Composable
internal fun NotificationRoute(
    navigateToBack: () -> Unit,
    navigateFromNotification: (NotificationSubType, String?) -> Unit,
    viewModel: ProfileNotificationViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.state) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileNotificationViewModel.Effect.NavigateToBack -> navigateToBack()
                is ProfileNotificationViewModel.Effect.NavigateFromNotification -> navigateFromNotification(
                    effect.subType,
                    effect.url,
                )
            }
        }
    }

    NotificationScreen(
        state = state,
        onClickBack = { viewModel.intent(ProfileNotificationViewModel.Intent.OnClickBack) },
        onClickCategory = { viewModel.intent(ProfileNotificationViewModel.Intent.OnClickCategory(it)) },
        onClickNotification = { viewModel.intent(ProfileNotificationViewModel.Intent.OnClickNotification(it)) },
        onDeleteNotification = { viewModel.intent(ProfileNotificationViewModel.Intent.OnDeleteNotification(it)) },
    )
}
