package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoApple
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoGoogle
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoKakao
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun SocialSignInButton(
    text: String,
    icon: ImageVector? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp),
        shape = RoundedCornerShape(size = 10.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFF5F5F5)),
        onClick = onClick
    ) {
        icon?.let {
            Image(
                imageVector = it,
                contentDescription = "",
            )
        }
        NonScaleText(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )

    }
}

@DevicePreviews
@Composable
private fun SocialSignInButtonPreview() {
    SocialSignInButton(
        text = "Test",
    ) {

    }
}

@DevicePreviews
@Composable
private fun SocialSignInAppleButtonPreview() {
    SocialSignInButton(
        text = "Test",
        icon = MyIconPack.IconLogoApple
    ) {

    }
}

@DevicePreviews
@Composable
private fun SocialSignInGoogleButtonPreview() {
    SocialSignInButton(
        text = "Test",
        icon = MyIconPack.IconLogoGoogle
    ) {

    }
}

@DevicePreviews
@Composable
private fun SocialSignInKakaoButtonPreview() {
    SocialSignInButton(
        text = "Test",
        icon = MyIconPack.IconLogoKakao
    ) {

    }
}