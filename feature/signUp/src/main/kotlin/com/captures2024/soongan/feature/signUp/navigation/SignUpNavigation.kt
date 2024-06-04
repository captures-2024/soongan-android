package com.captures2024.soongan.feature.signUp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.captures2024.soongan.feature.signUp.route.InputNicknameRoute

const val INPUT_NICKNAME_NAVIGATION_ROUTE = "input_nickname_route"
const val INPUT_BIRTH_YEAR_NAVIGATION_ROUTE = "input_birth_year_route"

fun NavController.navigateToInputNickname(navOptions: NavOptions? = null) {
    this.navigate(INPUT_NICKNAME_NAVIGATION_ROUTE, navOptions)
}

fun NavController.navigateToInputBirthYear(
    nickname: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$INPUT_BIRTH_YEAR_NAVIGATION_ROUTE/$nickname", navOptions)
}

fun NavGraphBuilder.signUp(
    navigateToBack: () -> Unit,
    navigateToInputBirthYear: (String, NavOptions) -> Unit,
) {
    composable(route = INPUT_NICKNAME_NAVIGATION_ROUTE) {
        InputNicknameRoute(
            navigateToBack = navigateToBack,
            navigateToInputBirthYear = navigateToInputBirthYear
        )
    }
    composable(
        route = "$INPUT_BIRTH_YEAR_NAVIGATION_ROUTE/{nickname}",
        arguments = listOf(
            navArgument("nickname") { type = NavType.StringType }
        )
    ) {

    }

}