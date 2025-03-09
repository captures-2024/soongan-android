package com.captures2024.soongan.feature.privacypolicy.route

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.core.viewmodel.sign.PrivacyPolicyViewModel
import com.captures2024.soongan.feature.privacypolicy.ui.PrivacyPolicyScreen

@Composable
internal fun PrivacyPolicyRoute(
    navigateToBack: () -> Unit,
    privacyPolicyViewModel: PrivacyPolicyViewModel = hiltViewModel(),
) {
    val uiState by privacyPolicyViewModel.state.collectAsState()

    val context = LocalContext.current

    val openBrowser = {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uiState.url))
        context.startActivity(browserIntent)
    }

    LaunchedEffect(Unit) {
        privacyPolicyViewModel.sideEffect.collect { effect ->
            when (effect) {
                is PrivacyPolicyViewModel.Effect.NavigateToBack -> navigateToBack()

                is PrivacyPolicyViewModel.Effect.NavigateToTerms -> openBrowser()
            }
        }
    }

    PrivacyPolicyScreen(
        intent = privacyPolicyViewModel::intent,
    )
}
