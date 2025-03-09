package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object HomeGalleryNavigator

fun NavController.navigateToHomeGallery() = navigate(HomeGalleryNavigator)

fun NavController.navigateToHomeGallery(navOptions: NavOptions?) = navigate(
    route = HomeGalleryNavigator,
    navOptions = navOptions,
)

fun NavController.setReportedPostId(postId: Long) {
    this.previousBackStackEntry?.savedStateHandle?.set(SAVED_STATE_HANDLE_KEY, postId)

    popBackStack<HomeGalleryNavigator>(inclusive = false)
}

fun NavController.getReportedPostId(): Long =
    this.currentBackStackEntry?.savedStateHandle?.get(SAVED_STATE_HANDLE_KEY) ?: -1L

private const val SAVED_STATE_HANDLE_KEY = "reported_post_id"
