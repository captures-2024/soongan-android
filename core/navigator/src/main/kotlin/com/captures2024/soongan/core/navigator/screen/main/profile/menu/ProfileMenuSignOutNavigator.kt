package com.captures2024.soongan.core.navigator.screen.main.profile.menu

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileMenuSignOutNavigator

fun NavController.navigateToProfileMenuSignOut() = navigateToProfileMenuSignOut(null)

fun NavController.navigateToProfileMenuSignOut(navOptions: NavOptions?) = navigate(
    route = ProfileMenuSignOutNavigator,
    navOptions = navOptions,
)
