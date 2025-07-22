package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.captures2024.soongan.presentation.feature.root.component.LoadingHostComponent
import kotlinx.coroutines.delay

@Composable
internal fun LoadingHost(visible: Pair<Boolean, Long>) {
    val animationVisible = remember(visible) { mutableStateOf(visible.first) }

    LaunchedEffect(visible) {
        if (visible.first) {
            delay(20000)
            animationVisible.value = false
        }
    }

    LoadingHostComponent(
        visible = animationVisible.value,
    )
}
