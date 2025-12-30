package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object FAQNavigator

fun NavController.navigateToFAQ() = navigateToFAQ(null)

fun NavController.navigateToFAQ(navOptions: NavOptions?) = navigate(
    route = FAQNavigator,
    navOptions = navOptions,
)
