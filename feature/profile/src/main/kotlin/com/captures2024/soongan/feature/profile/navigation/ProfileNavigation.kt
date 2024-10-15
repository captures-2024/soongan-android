package com.captures2024.soongan.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.ProfileNavigator
import com.captures2024.soongan.feature.profile.route.ProfileRoute

fun NavGraphBuilder.profile() {
    composable<ProfileNavigator> {
        ProfileRoute()
    }
}