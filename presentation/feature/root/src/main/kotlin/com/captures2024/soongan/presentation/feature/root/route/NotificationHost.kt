package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.model.dto.fcm.CloudMessage
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.navigator.screen.main.home.HomeNavigator
import com.captures2024.soongan.core.navigator.screen.main.post.navigateToPostInfo
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToExplain
import com.captures2024.soongan.core.navigator.screen.main.profile.navigateToProfile
import com.captures2024.soongan.presentation.viewmodel.root.NotificationViewModel

@Composable
internal fun NotificationHost(
    navController: NavController,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val state by viewModel.state.collectAsState()

    val notification = state.notification
    val cloudMessage = state.cloudMessage
    val isLoggedIn = state.isLoggedIn

    LaunchedEffect(
        notification,
        isLoggedIn,
    ) {
        analyticsHelper.d { "NotificationHost - notification: $notification, isLoggedIn: $isLoggedIn" }

        notification?.let { event ->
            if (!isLoggedIn) {
                return@LaunchedEffect
            }

            viewModel.intent(NotificationViewModel.Intent.ClearNotification)

            when (event.subType) {
                NotificationSubType.CONTEST_START -> {
                    // TODO navigate to home
                }

                NotificationSubType.CONTEST_END -> {
                    // TODO navigate to home
                }

                NotificationSubType.COMMENT -> {
                    // TODO navigate to comment
                }

                NotificationSubType.LIKE -> {
                    // TODO navigate to post
                }

                NotificationSubType.APPEAL -> {
                    // TODO navigate to appeal
                }

                NotificationSubType.NOTICE -> {
                    // TODO navigate to notice
                }
            }
        }
    }

    LaunchedEffect(
        cloudMessage,
        isLoggedIn,
    ) {
        analyticsHelper.d { "NotificationHost - cloudMessage: $cloudMessage, isLoggedIn: $isLoggedIn" }

        cloudMessage?.let { event ->
            if (!isLoggedIn) {
                return@LaunchedEffect
            }

            viewModel.intent(NotificationViewModel.Intent.ClearCloudMessage)

            when (event) {
                is CloudMessage.BlockMessageDto -> {
                    navController.navigateToProfile(
                        navOptions = buildTopLevelNavOptions(),
                    )
                    navController.navigateToPostInfo(
                        id = event.targetId,
                    )
                }

                is CloudMessage.CommentMessageDto -> {
                    // TODO navigate to comment
                }

                is CloudMessage.NeedExplainMessageDto -> {
                    navController.navigateToProfile(
                        navOptions = buildTopLevelNavOptions(),
                    )
                    navController.navigateToExplain(
                        postId = event.targetId,
                    )
                }

                is CloudMessage.ReportResultMessageDto -> {
                    // TODO ?
                }
            }
        }
    }
}

private fun buildTopLevelNavOptions(): NavOptions = navOptions {
    popUpTo(HomeNavigator) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}
