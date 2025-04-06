package com.captures2024.soongan.feature.profile.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography

@Composable
internal fun nonScaleAnnotatedTitle(
    title: String,
    titleFontSize: TextUnit = 12.sp,
    count: Int,
    countFontSize: TextUnit = 10.sp,
) = buildAnnotatedString {
    val commonParagraphStyle = ParagraphStyle(lineHeight = 24.sp)

    pushStyle(style = commonParagraphStyle)
    withStyle(
        style = getSGNonScaleSpanStyle(
            color = SGColor.primaryA,
            fontSize = titleFontSize,
            fontWeight = FontWeight.Bold,
            fontFamily = SGTypography.nanumSquareNeo,
            letterSpacing = (-5).em,
        ),
    ) {
        append(title)
    }

    append(" ")

    if(count > 0) {
        withStyle(
            style = getSGNonScaleSpanStyle(
                color = SGColor.primaryA,
                fontSize = countFontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (0).em,
            )
        ) {
            append("$count")
        }
    }
}
