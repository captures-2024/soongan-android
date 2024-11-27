package com.captures2024.soongan.feature.profile.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.util.getNonScaleSpanStyle

@Composable
internal fun getNonScaleAnnotatedStringTitle(
    title: String,
    titleFontSize: TextUnit,
    count: Int,
    countFontSize: TextUnit,
) = buildAnnotatedString {
    val commonParagraphStyle = ParagraphStyle(lineHeight = (titleFontSize.value + 6.sp.value).sp)
    val commonSpanStyle = SpanStyle(fontWeight = FontWeight.Bold)
    val titleStyle = commonSpanStyle + getNonScaleSpanStyle(fontSize = titleFontSize)
    val countStyle = commonSpanStyle + getNonScaleSpanStyle(fontSize = countFontSize)

    pushStyle(style = commonParagraphStyle)
    withStyle(style = titleStyle) {
        append(title)
    }
    append(" ")
    withStyle(style = countStyle) {
        append("$count")
    }
}