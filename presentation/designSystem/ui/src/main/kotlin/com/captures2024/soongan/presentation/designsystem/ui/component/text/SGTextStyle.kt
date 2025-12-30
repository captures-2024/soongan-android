package com.captures2024.soongan.presentation.designsystem.ui.component.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.extension.nonScaleSp
import com.captures2024.soongan.presentation.designsystem.ui.util.extension.normalizeLetterSpacing

@Stable
fun getSGTextStyle(
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit,
    fontFamily: FontFamily,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Unspecified,
): TextStyle = TextStyle(
    color = color,
    fontSize = fontSize,
    fontWeight = fontWeight,
    lineHeight = lineHeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
)

@Composable
fun getSGNonScaleTextStyle(
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit,
    fontFamily: FontFamily,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign = TextAlign.Unspecified,
): TextStyle = getSGTextStyle(
    color = color,
    fontSize = fontSize.nonScaleSp,
    fontWeight = fontWeight,
    lineHeight = lineHeight,
    fontFamily = fontFamily,
    letterSpacing = normalizeLetterSpacing(fontSize, letterSpacing).nonScaleSp,
    textDecoration = textDecoration,
    textAlign = textAlign,
)

@Composable
fun getSGNonScaleTextStyle(
    style: TextStyle,
) = getSGTextStyle(
    color = style.color,
    fontFamily = style.fontFamily ?: SGTypography.pretendard,
    fontSize = style.fontSize.nonScaleSp,
    fontWeight = style.fontWeight ?: FontWeight.Normal,
    lineHeight = style.lineHeight,
    letterSpacing = normalizeLetterSpacing(style.fontSize, style.letterSpacing).nonScaleSp,
    textDecoration = style.textDecoration,
    textAlign = style.textAlign,
)
