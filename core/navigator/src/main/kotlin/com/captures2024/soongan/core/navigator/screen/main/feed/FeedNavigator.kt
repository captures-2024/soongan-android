package com.captures2024.soongan.core.navigator.screen.main.feed

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class FeedNavigator(
    val round: Long? = null,
)

fun NavController.navigateToFeed(round: Long? = null) = navigateToFeed(
    round = round,
    navOptions = null,
)

fun NavController.navigateToFeed(
    navOptions: NavOptions?,
    round: Long? = null,
) = navigate(
    route = FeedNavigator(
        round = round,
    ),
    navOptions = navOptions,
)
