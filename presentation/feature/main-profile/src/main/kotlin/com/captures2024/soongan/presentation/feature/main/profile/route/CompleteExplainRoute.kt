package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.CompleteExplainScreen

@Composable
internal fun CompleteExplainRoute(
    navigateToBack: () -> Unit,
) {
    CompleteExplainScreen(
        onClickBack = navigateToBack,
    )
}
