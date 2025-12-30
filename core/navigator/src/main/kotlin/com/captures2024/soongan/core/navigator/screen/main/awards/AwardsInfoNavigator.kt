package com.captures2024.soongan.core.navigator.screen.main.awards

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class AwardsInfoNavigator(
    val id: Long,
)

fun NavController.navigateToAwardsInfo(id: Long) = navigateToAwardsInfo(
    id = id,
    navOptions = null,
)

fun NavController.navigateToAwardsInfo(
    id: Long,
    navOptions: NavOptions?,
) = navigate(
    route = AwardsInfoNavigator(
        id = id,
    ),
    navOptions = navOptions,
)
