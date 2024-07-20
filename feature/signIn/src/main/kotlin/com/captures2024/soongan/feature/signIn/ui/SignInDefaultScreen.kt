package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoApple
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoGoogle
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoKakao
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signIn.R

@Composable
internal fun SignInDefaultScreen(
    modifier: Modifier = Modifier,
    onClickAppleSignIn: () -> Unit = {},
    onClickGoogleSignIn: () -> Unit = {},
    onClickKakaoSignIn: () -> Unit = {},
    navigateToMain: (Boolean) -> Unit,
    navigateToTermsOfUse: (NavOptions) -> Unit = {},
    navigateToPrivacyPolicy: (NavOptions) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .paint(
                painter = painterResource(id = com.captures2024.soongan.core.design.R.drawable.image_background),
                contentScale = ContentScale.FillBounds,
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NonScaleText(
            text = stringResource(id = R.string.logo_text),
            color = Color(0xFFF5F5F5),
            fontSize = 60.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(32.dp))
        SocialSignInButton(
            text = stringResource(id = R.string.sign_in_text_apple),
            icon = MyIconPack.IconLogoApple,
            onClick = onClickAppleSignIn
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialSignInButton(
            text = stringResource(id = R.string.sign_in_text_google),
            icon = MyIconPack.IconLogoGoogle,
            onClick = onClickGoogleSignIn
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialSignInButton(
            text = stringResource(id = R.string.sign_in_text_kakao),
            icon = MyIconPack.IconLogoKakao,
            onClick = onClickKakaoSignIn
        )
        Spacer(modifier = Modifier.height(16.dp))
        TermsText(
            onClickTermsOfUse = {
                val options = NavOptions.Builder().build()
                navigateToTermsOfUse(options)
            },
            onClickPrivacyPolicy = {
                val options = NavOptions.Builder().build()
                navigateToPrivacyPolicy(options)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        NonScaleText(
            text = stringResource(id = R.string.gesture_looking),
            modifier = Modifier
                .clickable {
                    navigateToMain(true)
                },
            color = Color(0xFFF5F5F5),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@DevicePreviews
@Composable
private fun SignInDefaultScreenPreview() {
    SignInDefaultScreen(
        onClickGoogleSignIn = {},
        onClickKakaoSignIn = {},
        navigateToMain = {},
        navigateToTermsOfUse = {},
        navigateToPrivacyPolicy = {}
    )
}
