package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object CompleteExplainNavigator

fun NavController.navigateToCompleteExplain() = navigateToCompleteExplain(
    navOptions = null,
)

fun NavController.navigateToCompleteExplain(
    navOptions: NavOptions?,
) = navigate(
    route = CompleteExplainNavigator,
    navOptions = navOptions,
)
