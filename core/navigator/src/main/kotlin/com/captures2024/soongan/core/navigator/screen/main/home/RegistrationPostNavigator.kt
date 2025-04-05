package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object RegistrationPostNavigator

fun NavController.navigateToRegistrationPost() = navigateToRegistrationPost(null)

fun NavController.navigateToRegistrationPost(navOptions: NavOptions?) = navigate(
    route = RegistrationPostNavigator,
    navOptions = navOptions,
)
