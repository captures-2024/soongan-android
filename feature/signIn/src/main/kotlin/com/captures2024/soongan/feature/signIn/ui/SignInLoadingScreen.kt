package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun SignInLoadingScreen() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) {
    CircularProgressIndicator()
}

@DevicePreviews
@Composable
private fun SignInLoadingScreenPreview() {
    SignInLoadingScreen()
}