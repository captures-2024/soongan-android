package com.captures2024.soongan.feature.profile.ui.notification

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
import com.captures2024.soongan.core.designsystem.theme.PrimaryB

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(PrimaryB),
        contentAlignment = Alignment.Center,
    ) {
        NonScaleText(
            text = "notification",
            color = Color.Black,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}