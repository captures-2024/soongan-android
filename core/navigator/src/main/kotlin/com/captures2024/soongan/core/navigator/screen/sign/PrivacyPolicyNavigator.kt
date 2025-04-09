package com.captures2024.soongan.core.navigator.screen.sign

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object PrivacyPolicyNavigator

fun NavController.navigateToPrivacyPolicy() = navigateToPrivacyPolicy(null)

fun NavController.navigateToPrivacyPolicy(navOptions: NavOptions?) = navigate(
    route = PrivacyPolicyNavigator,
    navOptions = navOptions,
)
