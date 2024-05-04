package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.feature.signIn.PrivacyPolicyViewModel
import com.captures2024.soongan.feature.signIn.ui.PrivacyPolicyScreen

@Composable
internal fun PrivacyPolicyRoute(
    navigateToBack: () -> Unit,
    privacyPolicyViewModel: PrivacyPolicyViewModel = hiltViewModel()
) {
    PrivacyPolicyScreen(onClickBack = navigateToBack)
}