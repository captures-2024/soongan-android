package com.captures2024.soongan.presentation.designsystem.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.R

data object SGTypography {

    @Stable
    val pretendard: FontFamily = FontFamily(
        Font(R.font.pretendard_black, FontWeight.Black),
        Font(R.font.pretendard_black, FontWeight.Black, FontStyle.Italic),
        Font(R.font.pretendard_bold, FontWeight.Bold),
        Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.pretendard_light, FontWeight.Light),
        Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Italic),
        Font(R.font.pretendard_thin, FontWeight.Thin),
        Font(R.font.pretendard_thin, FontWeight.Thin, FontStyle.Italic),
        Font(R.font.pretendard_medium, FontWeight.Medium),
        Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.pretendard_regular, FontWeight.Normal),
        Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Italic),
        Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),
        Font(R.font.pretendard_extrabold, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.pretendard_extralight, FontWeight.ExtraLight),
        Font(R.font.pretendard_extralight, FontWeight.ExtraLight, FontStyle.Italic),
    )

    @Stable
    fun h1(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.1).em,
    )

    @Stable
    fun h2(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun h3(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun subtitle1(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun subtitle2(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun subtitle3(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun body1(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun body2(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun p1(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun p2(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun p3(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )

    @Stable
    fun p4(): TextStyle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = TextUnit.Unspecified,
        letterSpacing = (-0.08).em,
    )
}

@Preview
@Composable
private fun PreviewTypoPretendard() {
    SGTheme {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.h1(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.h2(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.h3(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.subtitle1(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.subtitle2(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.subtitle3(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.body1(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.body2(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.p1(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.p2(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.p3(),
            )

            Text(
                text = "맥도날드 감자튀김\n맥도날드 감자튀김",
                style = SGTypography.p4(),
            )
        }
    }
}
