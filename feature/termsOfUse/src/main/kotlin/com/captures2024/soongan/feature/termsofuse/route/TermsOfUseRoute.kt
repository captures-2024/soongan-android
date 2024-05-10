package com.captures2024.soongan.feature.termsofuse.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.feature.termsofuse.ui.TermsOfUseScreen
import com.captures2024.soongan.feature.termsofuse.viewmodel.TermsOfUseViewModel

@Composable
internal fun TermsOfUseRoute(
    navigateToBack: () -> Unit,
    termsOfUseViewModel: TermsOfUseViewModel = hiltViewModel()
) {
    TermsOfUseScreen(onClickBack = navigateToBack)
}