package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileEditNavigator

fun NavController.navigateToEditProfile() = navigateToEditProfile(null)

fun NavController.navigateToEditProfile(navOptions: NavOptions?) = navigate(
    route = ProfileEditNavigator,
    navOptions = navOptions,
)
