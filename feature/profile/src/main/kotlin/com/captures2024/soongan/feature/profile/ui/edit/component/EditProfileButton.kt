package com.captures2024.soongan.feature.profile.ui.edit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.theme.innerShadow

@Composable
internal fun EditProfileButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    val backgroundShape = RoundedCornerShape(8.dp)

    val customModifier: Modifier = when (enabled) {
        true -> modifier
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

        false -> modifier
            .background(
                color = SGColor.tempPrimaryC,
                shape = backgroundShape,
            )
            .innerShadow(
                shape = backgroundShape,
                color = Color.Black.copy(alpha = 0.25f),
                offsetX = 2.dp,
                offsetY = 2.dp,
                blur = 4.dp,
                spread = 0.dp,
            )
    }

    Box(
        modifier = customModifier
            .size(width = 120.dp, height = 48.dp)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        NonScaleText(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-5).em,
            lineHeight = 20.sp,
            color = when (enabled) {
                true -> SGColor.black
                false -> SGColor.white
            },
        )
    }
}