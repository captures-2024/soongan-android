package com.captures2024.soongan.presentation.feature.main.component.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.R
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.viewmodel.main.WelcomeViewModel

@Composable
internal fun WelcomeScreen(
    state: WelcomeViewModel.State,
    modifier: Modifier = Modifier,
) {
    val sb = StringBuilder()
        .append("환영합니다")
        .append("\n")
        .append("\n")
        .append(state.nickname)
        .append("님!")

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
        if (state.isInit) {
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
}

@DevicePreviews
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        state = WelcomeViewModel.State(
            isInit = true,
            nickname = "테스트",
        ),
    )
}
