package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileNavigator

fun NavController.navigateToProfile() = navigateToProfile(null)

fun NavController.navigateToProfile(navOptions: NavOptions?) = navigate(
    route = ProfileNavigator,
    navOptions = navOptions,
)
