package com.captures2024.soongan.core.navigator.screen.main.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class ExplainNavigator(
    val postId: Long,
)

fun NavController.navigateToExplain(
    postId: Long,
) = navigateToExplain(
    navOptions = null,
    postId = postId,
)

fun NavController.navigateToExplain(
    navOptions: NavOptions?,
    postId: Long,
) = navigate(
    route = ExplainNavigator(
        postId = postId,
    ),
    navOptions = navOptions,
)
