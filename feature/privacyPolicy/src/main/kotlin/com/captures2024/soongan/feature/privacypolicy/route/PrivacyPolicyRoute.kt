package com.captures2024.soongan.feature.privacypolicy.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.privacypolicy.ui.PrivacyPolicyScreen
import com.captures2024.soongan.feature.privacypolicy.PrivacyPolicyViewModel
import com.captures2024.soongan.feature.privacypolicy.state.PrivacyPolicySideEffect

@Composable
internal fun PrivacyPolicyRoute(
    navigateToBack: () -> Unit,
    privacyPolicyViewModel: PrivacyPolicyViewModel = hiltViewModel()
) {
    val uiState by privacyPolicyViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        privacyPolicyViewModel.sideEffect.collect {
            when (it) {
                is PrivacyPolicySideEffect.NavigateToBack -> navigateToBack()
            }
        }
    }

    PrivacyPolicyScreen(onClickBack = navigateToBack)
}