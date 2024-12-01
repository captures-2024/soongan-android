package com.captures2024.soongan.feature.sign.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.core.designsystem.component.SoonGanBackground
import com.captures2024.soongan.feature.sign.navigation.SignRouteNavHost
import com.captures2024.soongan.feature.signIn.SignInViewModel

@Composable
fun SignRoute(
    signInViewModel: SignInViewModel,
) {
    val navController: NavHostController = rememberNavController()

    SoonGanBackground {
        SignRouteNavHost(
            navController = navController,
            signInViewModel = signInViewModel,
        )
    }
}
