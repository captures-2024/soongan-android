package com.captures2024.soongan.core.navigator.screen.sign

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object TermsOfUseNavigator

fun NavController.navigateToTermsOfUse() = navigateToTermsOfUse(null)

fun NavController.navigateToTermsOfUse(navOptions: NavOptions?) = navigate(
    route = TermsOfUseNavigator,
    navOptions = navOptions,
)
