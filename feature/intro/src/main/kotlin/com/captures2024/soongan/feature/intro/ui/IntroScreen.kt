package com.captures2024.soongan.feature.intro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

@Composable
internal fun IntroScreen(
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier.fillMaxSize()
        .background(SGColor.white),
) {

}


@DevicePreviews
@Composable
private fun PreviewIntroScreen() {
    SGTheme {
        IntroScreen()
    }
}