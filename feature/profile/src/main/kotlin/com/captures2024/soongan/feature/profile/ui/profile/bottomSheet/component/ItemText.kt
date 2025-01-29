package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily

@Composable
internal fun ItemText(text: String) = NonScaleText(
    text = text,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = NanumSquareNeoFontFamily,
    letterSpacing = (-5).em,
    lineHeight = 24.sp
)