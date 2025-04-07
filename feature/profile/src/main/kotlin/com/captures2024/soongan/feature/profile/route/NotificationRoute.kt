package com.captures2024.soongan.feature.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.viewmodel.profile.NotificationViewModel
import com.captures2024.soongan.feature.profile.ui.notification.NotificationScreen

@Composable
internal fun NotificationRoute(
    navigateToBack: () -> Unit,
    navigateByNotification: (NotificationSubType, String?) -> Unit,
    notificationViewModel: NotificationViewModel = hiltViewModel(),
) {
    val uiState by notificationViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        notificationViewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                NotificationViewModel.Effect.NavigateToBack -> navigateToBack()

                is NotificationViewModel.Effect.NavigateByNotification -> navigateByNotification(sideEffect.subType, sideEffect.url)
            }
        }
    }

    NotificationScreen(
        uiState = uiState,
        intent = notificationViewModel::intent,
    )
}
