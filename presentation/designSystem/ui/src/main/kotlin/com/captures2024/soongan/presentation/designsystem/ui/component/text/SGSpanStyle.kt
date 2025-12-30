package com.captures2024.soongan.presentation.designsystem.ui.component.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.captures2024.soongan.presentation.designsystem.ui.util.extension.nonScaleSp
import com.captures2024.soongan.presentation.designsystem.ui.util.extension.normalizeLetterSpacing

@Stable
fun getSGSpanStyle(
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
): SpanStyle = SpanStyle(
    color = color,
    fontSize = fontSize,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
)

@Composable
fun getSGNonScaleSpanStyle(
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
): SpanStyle = getSGSpanStyle(
    color = color,
    fontSize = fontSize.nonScaleSp,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = normalizeLetterSpacing(fontSize, letterSpacing).nonScaleSp,
    textDecoration = textDecoration,
)
