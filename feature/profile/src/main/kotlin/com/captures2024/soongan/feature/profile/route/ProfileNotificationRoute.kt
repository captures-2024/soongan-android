package com.captures2024.soongan.feature.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.viewmodel.profile.ProfileNotificationViewModel
import com.captures2024.soongan.feature.profile.ui.notification.NotificationScreen

@Composable
internal fun ProfileNotificationRoute(
    navigateToBack: () -> Unit,
    navigateFromNotification: (NotificationSubType, String?) -> Unit,
    profileNotificationViewModel: ProfileNotificationViewModel = hiltViewModel(),
) {
    val uiState by profileNotificationViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileNotificationViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                ProfileNotificationViewModel.Effect.NavigateToBack -> navigateToBack()

                is ProfileNotificationViewModel.Effect.NavigateFromNotification -> navigateFromNotification(
                    sideEffect.subType,
                    sideEffect.url,
                )
            }
        }
    }

    NotificationScreen(
        uiState = uiState,
        intent = profileNotificationViewModel::intent,
    )
}
