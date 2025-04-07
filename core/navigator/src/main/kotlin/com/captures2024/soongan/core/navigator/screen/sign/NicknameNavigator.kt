package com.captures2024.soongan.core.navigator.screen.sign

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data object NicknameNavigator

fun NavController.navigateToNickname() = navigateToNickname(null)

fun NavController.navigateToNickname(navOptions: NavOptions?) = navigate(
    route = NicknameNavigator,
    navOptions = navOptions,
)
