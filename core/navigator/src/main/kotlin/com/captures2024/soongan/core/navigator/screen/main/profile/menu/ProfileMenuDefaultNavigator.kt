package com.captures2024.soongan.core.navigator.screen.main.profile.menu

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileMenuDefaultNavigator

fun NavController.navigateToProfileMenuDefault() = navigateToProfileMenuDefault(null)

fun NavController.navigateToProfileMenuDefault(navOptions: NavOptions?) = navigate(
    route = ProfileMenuDefaultNavigator,
    navOptions = navOptions,
)
