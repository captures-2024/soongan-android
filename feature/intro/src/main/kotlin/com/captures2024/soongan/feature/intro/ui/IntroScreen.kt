package com.captures2024.soongan.feature.intro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun IntroScreen(
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier.fillMaxSize()
        .background(Color.White),
) {

}


@DevicePreviews
@Composable
private fun PreviewIntroScreen() {
    SoonGanTheme {
        IntroScreen()
    }
}