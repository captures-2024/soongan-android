package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        NonScaleText(
            text = "home",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@DevicePreviews
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}