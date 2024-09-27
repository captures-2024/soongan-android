package com.captures2024.soongan.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.util.nonScaleSp

@Composable
fun NonScaleSpanStyle(
    fontSize: TextUnit,
    fontFamily: FontFamily = PretendardFontFamily,
    fontWeight: FontWeight = FontWeight(500),
    color: Color = Color(0xFF000000),
    textDecoration: TextDecoration? = null,
) = SpanStyle(
    fontSize = fontSize.nonScaleSp,
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    color = color,
    textDecoration = textDecoration
)
