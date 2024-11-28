package com.captures2024.soongan.feature.profile.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.util.nonScaleSpanStyle

@Composable
internal fun nonScaleAnnotatedTitle(
    title: String,
    titleFontSize: TextUnit,
    count: Int,
    countFontSize: TextUnit,
) = buildAnnotatedString {
    val commonParagraphStyle = ParagraphStyle(lineHeight = 24.sp)

    pushStyle(style = commonParagraphStyle)
    withStyle(style = nonScaleSpanStyle(fontSize = titleFontSize)) {
        append(title)
    }
    append(" ")
    withStyle(style = nonScaleSpanStyle(fontSize = countFontSize)) {
        append("$count")
    }
}