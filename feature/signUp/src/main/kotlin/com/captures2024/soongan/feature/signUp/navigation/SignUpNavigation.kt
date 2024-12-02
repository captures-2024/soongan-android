package com.captures2024.soongan.feature.signUp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.BirthDateNavigator
import com.captures2024.soongan.core.navigator.screen.sign.NicknameNavigator
import com.captures2024.soongan.feature.signUp.route.InputBirthDateRoute
import com.captures2024.soongan.feature.signUp.route.InputNicknameRoute

fun NavGraphBuilder.signUp(
    navigateToBack: () -> Unit,
    navigateToBirthDate: (String) -> Unit,
) {
    composable<NicknameNavigator> {
        InputNicknameRoute(
            navigateToBack = navigateToBack,
            navigateToBirthDate = navigateToBirthDate
        )
    }
    composable<BirthDateNavigator> {
        InputBirthDateRoute(
            navigateToBack = navigateToBack,
            navigateToMain = {}
        )
    }
}