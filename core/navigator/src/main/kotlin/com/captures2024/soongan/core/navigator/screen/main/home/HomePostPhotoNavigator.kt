package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class HomePostPhotoNavigator(
    val url: String,
)

fun NavController.navigateToHomePostPhoto(url: String) = navigateToHomePostPhoto(
    url = url,
    navOptions = null,
)

fun NavController.navigateToHomePostPhoto(
    url: String,
    navOptions: NavOptions?,
) = navigate(
    route = HomePostPhotoNavigator(url),
    navOptions = navOptions,
)
