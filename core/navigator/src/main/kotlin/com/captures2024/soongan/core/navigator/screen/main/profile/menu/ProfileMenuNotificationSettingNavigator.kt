package com.captures2024.soongan.core.navigator.screen.main.profile.menu

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileMenuNotificationSettingNavigator

fun NavController.navigateToProfileMenuNotificationSetting() = navigateToProfileMenuNotificationSetting(null)

fun NavController.navigateToProfileMenuNotificationSetting(navOptions: NavOptions?) = navigate(
    route = ProfileMenuNotificationSettingNavigator,
    navOptions = navOptions,
)
