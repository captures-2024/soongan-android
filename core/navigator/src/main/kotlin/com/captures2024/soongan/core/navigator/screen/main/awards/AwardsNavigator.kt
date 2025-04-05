package com.captures2024.soongan.core.navigator.screen.main.awards

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object AwardsNavigator

fun NavController.navigateToAwards() = navigateToAwards(null)

fun NavController.navigateToAwards(navOptions: NavOptions?) = navigate(
    route = AwardsNavigator,
    navOptions = navOptions,
)
