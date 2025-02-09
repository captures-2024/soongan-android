package com.captures2024.soongan.feature.termsofuse.route

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.core.viewmodel.sign.TermsOfUseViewModel
import com.captures2024.soongan.feature.termsofuse.ui.TermsOfUseScreen

@Composable
internal fun TermsOfUseRoute(
    navigateToBack: () -> Unit,
    viewModel: TermsOfUseViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsState()

    val context = LocalContext.current

    val openBrowser = {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uiState.url))
        context.startActivity(browserIntent)
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is TermsOfUseViewModel.Effect.NavigateToBack -> navigateToBack()

                is TermsOfUseViewModel.Effect.NavigateToTerms -> openBrowser()
            }
        }
    }

    TermsOfUseScreen(
        intent = viewModel::intent,
    )
}