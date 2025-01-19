package com.captures2024.soongan.core.navigator.screen.main.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class HomePostNavigator(
    val id: Int,
)

fun NavController.navigateToHomePost(id: Int) = navigate(HomePostNavigator(id = id))

fun NavController.navigateToHomePost(
    id: Int,
    navOptions: NavOptions?,
) = navigate(
    route = HomePostNavigator(
        id = id,
    ),
    navOptions = navOptions
)
