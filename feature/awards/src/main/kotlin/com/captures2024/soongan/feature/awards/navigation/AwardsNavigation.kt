package com.captures2024.soongan.feature.awards.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.AwardsNavigator
import com.captures2024.soongan.feature.awards.route.AwardsRoute

fun NavGraphBuilder.awards() {
    composable<AwardsNavigator> {
        AwardsRoute()
    }
}
