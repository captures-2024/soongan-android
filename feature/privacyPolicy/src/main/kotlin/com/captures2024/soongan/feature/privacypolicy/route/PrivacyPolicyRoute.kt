package com.captures2024.soongan.feature.privacypolicy.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.feature.privacypolicy.ui.PrivacyPolicyScreen
import com.captures2024.soongan.feature.privacypolicy.PrivacyPolicyViewModel

@Composable
internal fun PrivacyPolicyRoute(
    navigateToBack: () -> Unit,
    privacyPolicyViewModel: PrivacyPolicyViewModel = hiltViewModel()
) {
    PrivacyPolicyScreen(onClickBack = navigateToBack)
}