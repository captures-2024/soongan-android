package com.captures2024.soongan.core.navigator.screen.main.awards

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class AwardsInfoNavigator(
    val round: Int,
)

fun NavController.navigateToAwardsInfo(round: Int) = navigateToAwardsInfo(
    round = round,
    navOptions = null,
)

fun NavController.navigateToAwardsInfo(
    round: Int,
    navOptions: NavOptions?,
) = navigate(
    route = AwardsInfoNavigator(
        round = round,
    ),
    navOptions = navOptions,
)
