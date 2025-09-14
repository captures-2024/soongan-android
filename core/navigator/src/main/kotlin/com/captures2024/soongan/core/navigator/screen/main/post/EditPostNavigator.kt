package com.captures2024.soongan.core.navigator.screen.main.post

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class EditPostNavigator(
    val postId: Long,
    val imageUrl: String,
    val title: String,
    val roundId: Int,
    val subject: String,
)

fun NavController.navigateToEditPost(
    id: Long,
    imageUrl: String,
    title: String,
    roundId: Int,
    subject: String,
) = navigateToEditPost(
    id = id,
    imageUrl = imageUrl,
    title = title,
    roundId = roundId,
    subject = subject,
    navOptions = null,
)

fun NavController.navigateToEditPost(
    id: Long,
    imageUrl: String,
    title: String,
    roundId: Int,
    subject: String,
    navOptions: NavOptions?,
) = navigate(
    route = EditPostNavigator(
        postId = id,
        imageUrl = imageUrl,
        title = title,
        roundId = roundId,
        subject = subject,
    ),
    navOptions = navOptions,
)
