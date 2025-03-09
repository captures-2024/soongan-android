package com.captures2024.soongan.feature.signUp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.BirthNavigator
import com.captures2024.soongan.core.navigator.screen.sign.NicknameNavigator
import com.captures2024.soongan.feature.signUp.route.BirthRoute
import com.captures2024.soongan.feature.signUp.route.NicknameRoute

fun NavGraphBuilder.signUp(
    navigateToBack: () -> Unit,
    navigateToBirth: (String) -> Unit,
) {
    composable<NicknameNavigator> {
        NicknameRoute(
            navigateToBack = navigateToBack,
            navigateToBirth = navigateToBirth,
        )
    }
    composable<BirthNavigator> {
        BirthRoute(
            navigateToBack = navigateToBack,
        )
    }
}
