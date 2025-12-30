package com.captures2024.soongan.presentation.feature.root.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor

@Composable
internal fun LoadingHostComponent(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.fillMaxSize(),
        enter = fadeIn(animationSpec = tween(delayMillis = 100)),
        exit = fadeOut(animationSpec = tween(delayMillis = 100)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SGColor.transparent)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {},
                ),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                color = SGColor.primaryA,
                strokeWidth = 4.dp,
                trackColor = SGColor.transparent,
                strokeCap = StrokeCap.Round,
            )
        }
    }
}
