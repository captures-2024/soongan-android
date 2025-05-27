package com.captures2024.soongan.presentation.feature.main.profile.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography

@Composable
internal fun nonScaleAnnotatedTitle(
    title: String,
    count: Int,
) = buildAnnotatedString {
    val commonParagraphStyle = ParagraphStyle(lineHeight = 24.sp)

    pushStyle(style = commonParagraphStyle)
    withStyle(
        style = getSGNonScaleSpanStyle(
            color = SGColor.Grayscale.black100,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = SGTypography.pretendard,
            letterSpacing = (-5).em,
        ),
    ) {
        append(title)
    }

    append(" ")

    withStyle(
        style = getSGNonScaleSpanStyle(
            color = SGColor.Grayscale.black100,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = SGTypography.pretendard,
            letterSpacing = (0).em,
        ),
    ) {
        append("$count")
    }
}
