package com.captures2024.soongan.core.navigator.screen.main.post

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class ImageViewerNavigator(
    val url: String,
)

fun NavController.navigateToImageViewer(url: String) = navigateToImageViewer(
    url = url,
    navOptions = null,
)

fun NavController.navigateToImageViewer(
    url: String,
    navOptions: NavOptions?,
) = navigate(
    route = ImageViewerNavigator(url),
    navOptions = navOptions,
)
