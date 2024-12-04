package com.captures2024.soongan.feature.sign.route

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.feature.sign.navigation.SignRouteNavHost
import com.captures2024.soongan.feature.signIn.SignInViewModel

@Composable
fun SignRoute(
    signInViewModel: SignInViewModel,
) {
    val navController: NavHostController = rememberNavController()

    Scaffold { padding ->
        SignRouteNavHost(
            navController = navController,
            signInViewModel = signInViewModel,
            modifier = Modifier.padding(padding)
        )
    }
}
