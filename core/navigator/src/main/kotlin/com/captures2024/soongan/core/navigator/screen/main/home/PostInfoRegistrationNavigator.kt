package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object PostInfoRegistrationNavigator

fun NavController.navigateToPostInfoRegistration() = navigateToPostInfoRegistration(null)

fun NavController.navigateToPostInfoRegistration(navOptions: NavOptions?) = navigate(
    route = PostInfoRegistrationNavigator,
    navOptions = navOptions,
)
