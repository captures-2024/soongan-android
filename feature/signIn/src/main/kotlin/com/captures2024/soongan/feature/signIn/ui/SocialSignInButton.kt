package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun SocialSignInButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp),
        shape = RoundedCornerShape(size = 10.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        onClick = onClick
    ) {
        NonScaleText(
            text = text,
            fontSize = 13.sp,
            lineHeight = 8.sp,
            fontWeight = FontWeight(600),
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
    }
}

@DevicePreviews
@Composable
private fun SocialSignInButtonPreview() {
    SocialSignInButton(
        text = "Test"
    ) {

    }
}
