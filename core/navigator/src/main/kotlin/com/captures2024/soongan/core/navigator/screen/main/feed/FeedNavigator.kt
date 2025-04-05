package com.captures2024.soongan.core.navigator.screen.main.feed

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object FeedNavigator

fun NavController.navigateToFeed() = navigateToFeed(null)

fun NavController.navigateToFeed(navOptions: NavOptions?) = navigate(
    route = FeedNavigator,
    navOptions = navOptions,
)
