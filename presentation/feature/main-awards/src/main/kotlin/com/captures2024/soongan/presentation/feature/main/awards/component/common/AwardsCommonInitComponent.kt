package com.captures2024.soongan.presentation.feature.main.awards.component.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun AwardsCommonInitComponent(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier)
}

@DevicePreviews
@Composable
private fun AwardsCommonInitComponent_Preview() {
    SGTheme {
        AwardsCommonInitComponent(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
