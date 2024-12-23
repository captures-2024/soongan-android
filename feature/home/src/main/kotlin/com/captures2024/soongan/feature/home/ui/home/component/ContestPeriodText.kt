package com.captures2024.soongan.feature.home.ui.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.SGColor

@Composable
internal fun ContestPeriodText(
    text: String,
    period: String,
) {
    NonScaleText(
        text = "$text | $period",
        fontSize = 15.sp,
        color = SGColor.primaryA,
        fontWeight = FontWeight.SemiBold,
    )
}