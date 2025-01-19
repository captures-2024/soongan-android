package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object NotificationNavigator

fun NavController.navigateToNotification() = navigate(NotificationNavigator)

fun NavController.navigateToNotification(navOptions: NavOptions?) = navigate(
    route = NotificationNavigator,
    navOptions = navOptions,
)
