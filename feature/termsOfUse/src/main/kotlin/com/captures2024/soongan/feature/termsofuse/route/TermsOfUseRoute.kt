package com.captures2024.soongan.feature.termsofuse.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.termsofuse.ui.TermsOfUseScreen
import com.captures2024.soongan.feature.termsofuse.TermsOfUseViewModel
import com.captures2024.soongan.feature.termsofuse.state.TermsOfUseIntent
import com.captures2024.soongan.feature.termsofuse.state.TermsOfUseSideEffect

@Composable
internal fun TermsOfUseRoute(
    navigateToBack: () -> Unit,
    termsOfUseViewModel: TermsOfUseViewModel = hiltViewModel()
) {
    val uiState by termsOfUseViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        termsOfUseViewModel.sideEffect.collect {
            when (it) {
                is TermsOfUseSideEffect.NavigateToBack -> navigateToBack()
            }
        }
    }

    TermsOfUseScreen(
        onClickBack = { termsOfUseViewModel.intent(TermsOfUseIntent.OnClickBack) }
    )
}