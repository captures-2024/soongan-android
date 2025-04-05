package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object HomeGalleryNavigator

fun NavController.navigateToHomeGallery() = navigateToHomeGallery(null)

fun NavController.navigateToHomeGallery(navOptions: NavOptions?) = navigate(
    route = HomeGalleryNavigator,
    navOptions = navOptions,
)
