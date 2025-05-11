package com.captures2024.soongan.core.navigator.screen.sign

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object SignUpNavigator

fun NavController.navigateToSignUp() = navigateToSignUp(null)

fun NavController.navigateToSignUp(navOptions: NavOptions?) = navigate(
    route = SignUpNavigator,
    navOptions = navOptions,
)
