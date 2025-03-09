package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoKakao
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signIn.R

@Composable
internal fun SignInScreen(
    intent: (SignViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .paint(
                painter = painterResource(id = com.captures2024.soongan.core.designsystem.ui.R.drawable.image_background),
                contentScale = ContentScale.FillBounds,
            )
            .padding(all = 16.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            SGText(
                text = stringResource(id = R.string.logo_text),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryB,
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 112.sp,
                    fontFamily = SGTypography.pretendard,
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 25.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SocialSignInButton(
                text = stringResource(id = R.string.sign_in_text_kakao),
                icon = MyIconPack.IconLogoKakao,
                backgroundColor = Color(0xFFFEE500),
                onClick = { intent(SignViewModel.Intent.OnClickSignKakao) },
            )
            HeightSpacer(16.dp)
            Spacer(modifier = Modifier.height(16.dp))
            TermsText(
                onClickTermsOfUse = { intent(SignViewModel.Intent.OnClickTermsOfUse) },
                onClickPrivacyPolicy = { intent(SignViewModel.Intent.OnClickPrivacyPolicy) },
            )
            HeightSpacer(24.dp)
            SGText(
                text = stringResource(id = R.string.gesture_looking),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryB,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                ),
                modifier = Modifier
                    .clickable { intent(SignViewModel.Intent.OnClickGuestMode) },
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewSignInScreen() {
    SignInScreen(
        intent = {},
    )
}

