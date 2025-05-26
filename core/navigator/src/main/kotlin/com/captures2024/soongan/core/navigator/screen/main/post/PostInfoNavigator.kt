package com.captures2024.soongan.core.navigator.screen.main.post

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class PostInfoNavigator(
    val id: Long,
)

fun NavController.navigateToPostInfo(id: Long) = navigateToPostInfo(
    id = id,
    navOptions = null,
)

fun NavController.navigateToPostInfo(
    id: Long,
    navOptions: NavOptions?,
) = navigate(
    route = PostInfoNavigator(
        id = id,
    ),
    navOptions = navOptions,
)
