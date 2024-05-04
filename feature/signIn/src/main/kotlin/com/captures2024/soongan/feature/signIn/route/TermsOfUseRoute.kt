package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.feature.signIn.TermsOfUseViewModel
import com.captures2024.soongan.feature.signIn.ui.TermsOfUseScreen

@Composable
internal fun TermsOfUseRoute(
    navigateToBack: () -> Unit,
    termsOfUseViewModel: TermsOfUseViewModel = hiltViewModel()
) {
    TermsOfUseScreen(onClickBack = navigateToBack)
}