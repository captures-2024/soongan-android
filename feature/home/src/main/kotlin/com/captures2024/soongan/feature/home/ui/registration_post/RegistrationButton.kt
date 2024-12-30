package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.theme.innerShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun RegistrationButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    val backgroundShape = RoundedCornerShape(4.dp)

    val customModifier: Modifier = when (enabled) {
        true -> modifier.width(96.dp)
            .height(40.dp)
            .dropShadow(
                shape = backgroundShape,
                offsetX = 2.dp,
                offsetY = 2.dp,
                blur = 4.dp
            )
            .background(
                color = SGColor.accent,
                shape = backgroundShape,
            )
            .clickable(onClick = onClick)
        
        false -> modifier.width(96.dp)
            .height(40.dp)
            .dropShadow(
                shape = backgroundShape,
                offsetX = 2.dp,
                offsetY = 2.dp,
                blur = 4.dp
            )
            .background(
                color = SGColor.tempPrimaryC,
                shape = backgroundShape,
            )
            .innerShadow(
                shape = backgroundShape,
                color = SGColor.black.copy(alpha = 0.25f),
                offsetX = 2.dp,
                offsetY = 2.dp,
                blur = 4.dp,
                spread = 0.dp,
            )
    }

    Box(
        modifier = customModifier,
        contentAlignment = Alignment.Center,
    ) {
        NonScaleText(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 16.sp,
            color = when (enabled) {
                true -> SGColor.black
                false -> SGColor.white
            },
        )
    }
}

@DevicePreviews
@Composable
private fun RegistrationButtonNotEnabledPreview() {
    RegistrationButton(
        text = "제출하기",
        onClick = {},
    )
}

@DevicePreviews
@Composable
private fun RegistrationButtonEnabledPreview() {
    RegistrationButton(
        text = "제출하기",
        onClick = {},
        enabled = true,
    )
}