package com.captures2024.soongan.core.navigator.screen.main.profile.menu

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object ProfileMenuWithdrawNavigator

fun NavController.navigateToProfileMenuWithdraw() = navigateToProfileMenuWithdraw(null)

fun NavController.navigateToProfileMenuWithdraw(navOptions: NavOptions?) = navigate(
    route = ProfileMenuWithdrawNavigator,
    navOptions = navOptions,
)
