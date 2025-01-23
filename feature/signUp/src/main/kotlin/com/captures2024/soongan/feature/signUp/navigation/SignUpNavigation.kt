package com.captures2024.soongan.feature.signUp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.sign.BirthNavigator
import com.captures2024.soongan.core.navigator.screen.sign.NicknameNavigator
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signUp.route.InputBirthRoute
import com.captures2024.soongan.feature.signUp.route.InputNicknameRoute

fun NavGraphBuilder.signUp(
    navigateToBack: () -> Unit,
    navigateToBirth: (String) -> Unit,
) {
    composable<NicknameNavigator> {
        InputNicknameRoute(
            navigateToBack = navigateToBack,
            navigateToBirth = navigateToBirth
        )
    }
    composable<BirthNavigator> {
        InputBirthRoute(
            navigateToBack = navigateToBack,
        )
    }
}