package com.captures2024.soongan.feature.welcome.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.R
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.welcome.WelcomeViewModel

@Composable
internal fun WelcomeScreen(
    uiState: WelcomeViewModel.State,
    modifier: Modifier = Modifier,
) {
    val sb = StringBuilder()
        .append(stringResource(id = com.captures2024.soongan.feature.welcome.R.string.welcome_text))
        .append("\n")
        .append("\n")
        .append(uiState.nickname)
        .append(stringResource(id = com.captures2024.soongan.feature.welcome.R.string.nickname_unit_text))

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.black)
            .paint(
                painter = painterResource(id = R.drawable.image_background),
                contentScale = ContentScale.FillBounds,
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = sb.toString(),
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryB,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 44.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
                textAlign = TextAlign.Center,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        uiState = WelcomeViewModel.State("테스트"),
    )
}
