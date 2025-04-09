package com.captures2024.soongan.core.navigator.screen.sign

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class BirthNavigator(
    val nickname: String,
)

fun NavController.navigateToBirth(nickname: String) = navigateToBirth(
    nickname = nickname,
    navOptions = null,
)

fun NavController.navigateToBirth(
    nickname: String,
    navOptions: NavOptions?,
) = navigate(
    route = BirthNavigator(nickname),
    navOptions = navOptions,
)
