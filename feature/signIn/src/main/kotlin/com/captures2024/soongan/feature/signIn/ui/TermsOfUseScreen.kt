package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun TermsOfUseScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TermsOfUseTopBarScreen(onClickBack = onClickBack)
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TermsOfUseScreen",
                color = Color.White
            )
        }
    }
}

@DevicePreviews
@Composable
private fun TermsOfUseScreenPreview() {
    TermsOfUseScreen(
        onClickBack = {}
    )
}