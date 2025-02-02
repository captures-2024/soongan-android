package com.captures2024.soongan.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.util.extension.nonScaleSp
import com.captures2024.soongan.core.designsystem.util.extension.normalizeLetterSpacing

@Composable
fun nonScaleSpanStyle(
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
    textDecoration = textDecoration,
)


@Composable
fun nonScaleTextStyle(
    fontSize: TextUnit,
    fontFamily: FontFamily = PretendardFontFamily,
    fontWeight: FontWeight = FontWeight(500),
    letterSpacing: TextUnit,
    color: Color = Color(0xFF000000),
    textDecoration: TextDecoration? = null,
) = TextStyle(
    fontSize = fontSize.nonScaleSp,
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    letterSpacing = normalizeLetterSpacing(fontSize, letterSpacing).nonScaleSp,
    color = color,
    textDecoration = textDecoration,
)