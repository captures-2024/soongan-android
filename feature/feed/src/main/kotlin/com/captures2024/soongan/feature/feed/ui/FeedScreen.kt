package com.captures2024.soongan.feature.feed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun FeedScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(SGColor.primaryB),
        contentAlignment = Alignment.Center,
    ) {
        NonScaleText(
            text = "feed",
            color = SGColor.black,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@DevicePreviews
@Composable
private fun FeedScreenPreview() {
    FeedScreen()
}
