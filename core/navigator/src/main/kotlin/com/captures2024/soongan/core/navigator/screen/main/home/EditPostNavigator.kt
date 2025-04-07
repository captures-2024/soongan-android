package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class EditPostNavigator(
    val postId: Long,
    val imageUrl: String,
    val title: String,
)

fun NavController.navigateToEditPost(
    id: Long,
    imageUrl: String,
    title: String,
) = navigateToEditPost(
    id = id,
    imageUrl = imageUrl,
    title = title,
    navOptions = null,
)

fun NavController.navigateToEditPost(
    id: Long,
    imageUrl: String,
    title: String,
    navOptions: NavOptions?,
) = navigate(
    route = EditPostNavigator(
        postId = id,
        imageUrl = imageUrl,
        title = title,
    ),
    navOptions = navOptions,
)
