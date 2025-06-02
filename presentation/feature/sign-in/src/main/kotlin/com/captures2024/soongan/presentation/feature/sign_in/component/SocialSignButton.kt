package com.captures2024.soongan.presentation.feature.sign_in.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconLogoGoogle
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconLogoKakao
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.sign_in.R

@Composable
internal fun GoogleSignButton(onClick: () -> Unit) {
    SocialSignButton(
        content = stringResource(R.string.google_sign_content),
        leadingIcon = MyIconPack.IconLogoGoogle,
        backgroundColor = Color(0xFFF5F5F5),
        onClick = onClick,
    )
}

@Composable
internal fun KakaoSignButton(onClick: () -> Unit) {
    SocialSignButton(
        content = stringResource(R.string.kakao_sign_content),
        leadingIcon = MyIconPack.IconLogoKakao,
        backgroundColor = Color(0xFFFEE500),
        onClick = onClick,
    )
}

@Composable
private fun SocialSignButton(
    content: String,
    leadingIcon: ImageVector?,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(size = 10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 32.dp,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.let {
                Image(
                    imageVector = it,
                    contentDescription = stringResource(R.string.sign_leading_icon_description),
                )
            }

            SGText(
                text = content,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewGoogleSignButton() {
    SGBackground {
        GoogleSignButton {}
    }
}

@DevicePreviews
@Composable
private fun PreviewKakaoSignButton() {
    SGBackground {
        KakaoSignButton {}
    }
}
