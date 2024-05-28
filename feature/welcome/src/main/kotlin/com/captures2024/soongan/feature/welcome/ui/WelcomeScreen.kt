package com.captures2024.soongan.feature.welcome.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.welcome.WelcomeUiState

@Composable
internal fun WelcomeScreen(
    modifier: Modifier = Modifier,
    uiState: WelcomeUiState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .paint(
                painter = painterResource(id = R.drawable.image_background),
                contentScale = ContentScale.FillBounds,
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NonScaleText(
            text = stringResource(id = com.captures2024.soongan.feature.welcome.R.string.welcome_text),
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(44.dp))
        NonScaleText(
            text = when (uiState) {
                is WelcomeUiState.Loading -> ""
                else -> uiState.nickname + stringResource(id = com.captures2024.soongan.feature.welcome.R.string.nickname_unit_text)
            },
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@DevicePreviews
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        uiState = WelcomeUiState.Success("테스트")
    )
}