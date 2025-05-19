package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun HomeInitComponent(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier)
}

@DevicePreviews
@Composable
private fun PreviewHomeInitComponent() {
    SGTheme {
        HomeInitComponent(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
