package com.captures2024.soongan.presentation.feature.root.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.R
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = SGColor.BG.background),
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.sign_logo),
                contentDescription = "sign_logo",
                modifier = Modifier.widthIn(max = 131.dp)
                    .heightIn(max = 170.dp),
            )
        }

        Spacer(modifier = Modifier.weight(0.7f))
    }
}

@DevicePreviews
@Composable
private fun PreviewSplashScreen() {
    SGBackground {
        SplashScreen()
    }
}
