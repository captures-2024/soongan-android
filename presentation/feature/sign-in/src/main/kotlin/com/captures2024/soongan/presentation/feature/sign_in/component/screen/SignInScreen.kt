package com.captures2024.soongan.presentation.feature.sign_in.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.captures2024.soongan.core.designsystem.ui.R
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.sign_in.component.GoogleSignButton
import com.captures2024.soongan.presentation.feature.sign_in.component.KakaoSignButton
import com.captures2024.soongan.presentation.feature.sign_in.component.TermsComponent

@Composable
internal fun SignInScreen(
    modifier: Modifier = Modifier,
    onClickSignGoogle: () -> Unit,
    onClickSignKakao: () -> Unit,
    onClickTerms: () -> Unit,
    onClickPrivacyPolicy: () -> Unit,
    onClickGuestMode: () -> Unit,
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFF848381))
            .paint(
                painter = painterResource(id = R.drawable.image_background),
                contentScale = ContentScale.FillBounds,
            ),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.sign_logo),
                contentDescription = "sign_logo",
                modifier = Modifier.widthIn(max = 131.dp)
                    .heightIn(max = 170.dp),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(bottom = 25.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GoogleSignButton(onClick = onClickSignGoogle)

            HeightSpacer(16.dp)

            KakaoSignButton(onClick = onClickSignKakao)

            HeightSpacer(16.dp)

            TermsComponent(
                onClickTerms = onClickTerms,
                onClickPrivacyPolicy = onClickPrivacyPolicy,
            )

            HeightSpacer(24.dp)

            SGText(
                text = stringResource(com.captures2024.soongan.presentation.feature.sign_in.R.string.guest_mode_content),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                ),
                modifier = Modifier.clickable(onClick = onClickGuestMode),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewSignInScreen() {
    SGBackground {
        SignInScreen(
            onClickSignGoogle = {},
            onClickSignKakao = {},
            onClickTerms = {},
            onClickPrivacyPolicy = {},
            onClickGuestMode = {},
        )
    }
}
