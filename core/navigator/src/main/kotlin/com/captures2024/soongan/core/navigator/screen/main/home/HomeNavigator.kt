package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object HomeNavigator

fun NavController.navigateToHome() = navigate(HomeNavigator)

fun NavController.navigateToHome(navOptions: NavOptions?) = navigate(
    route = HomeNavigator,
    navOptions = navOptions,
)
