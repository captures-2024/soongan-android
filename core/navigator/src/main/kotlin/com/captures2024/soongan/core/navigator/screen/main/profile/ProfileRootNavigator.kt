package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRootNavigator {

    @Serializable
    data object ProfileNavigator

    @Serializable
    data object EditNavigator
}

fun NavController.navigateToProfile() = navigate(ProfileRootNavigator)

fun NavController.navigateToProfile(navOptions: NavOptions?) = navigate(
    route = ProfileRootNavigator,
    navOptions = navOptions,
)

fun NavController.navigateToEditProfile() = navigate(ProfileRootNavigator.EditNavigator)

fun NavController.navigateToEditProfile(navOptions: NavOptions?) = navigate(
    route = ProfileRootNavigator.EditNavigator,
    navOptions = navOptions,
)


