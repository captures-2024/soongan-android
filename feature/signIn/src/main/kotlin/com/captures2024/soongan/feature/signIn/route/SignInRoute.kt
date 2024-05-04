package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen

@Composable
internal fun SignInRoute(
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    SignInDefaultScreen()
}