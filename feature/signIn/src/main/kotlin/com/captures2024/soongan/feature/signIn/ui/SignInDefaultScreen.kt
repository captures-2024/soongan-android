package com.captures2024.soongan.feature.signIn.ui

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signIn.R

@Composable
internal fun SignInDefaultScreen(
    modifier: Modifier = Modifier,
    onClickGoogleSignIn: () -> Unit,
    onClickKakaoSignIn: () -> Unit,
    onClickTermsOfUse: () -> Unit,
    onClickPrivacyPolicy: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SocialSignInButton(text = "Social Sign In") {
            /* TODO */
        }
        Spacer(modifier = Modifier.height(16.dp))
        SocialSignInButton(
            text = "Social Sign In",
            onClick = onClickGoogleSignIn
        )
        Spacer(modifier = Modifier.height(16.dp))
        SocialSignInButton(
            text = "Social Sign In",
            onClick = onClickKakaoSignIn
        )
        Spacer(modifier = Modifier.height(16.dp))
        TermsText(
            onClickTermsOfUse = onClickTermsOfUse,
            onClickPrivacyPolicy = onClickPrivacyPolicy
        )
        Spacer(modifier = Modifier.height(24.dp))
        NonScaleText(
            text = stringResource(id = R.string.gesture_looking),
            modifier = Modifier
                .clickable {
                    // TODO onClick looking
                },
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight(500)
        )
    }
}

@DevicePreviews
@Composable
private fun SignInDefaultScreenPreview() {
    SignInDefaultScreen(
        onClickGoogleSignIn = {},
        onClickKakaoSignIn = {},
        onClickTermsOfUse = {},
        onClickPrivacyPolicy = {},
    )
}
