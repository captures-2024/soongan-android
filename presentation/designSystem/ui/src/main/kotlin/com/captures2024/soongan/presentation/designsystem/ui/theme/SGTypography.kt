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
import androidx.compose.ui.unit.dp
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
    fun h1(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 50

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 54.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun h2(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 40

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 44.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun h3(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 28

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 32.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun h4(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 20

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 20.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun h5(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 18

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 22.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun h6(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 16

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 20.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun p2(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 16

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 24.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun p3(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 12

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 20.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }

    @Stable
    fun small(
        fontFamily: FontFamily,
        fontWeight: FontWeight,
    ): TextStyle {
        val fontSize = 12

        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            lineHeight = 12.sp,
            letterSpacing = (fontSize * -0.05).sp,
        )
    }
}

@Preview
@Composable
private fun PreviewTypoPretendard() {
    SGTheme {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h1(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h2(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h3(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h4(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h5(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h6(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p2(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p3(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.small(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                ),
            )
        }
    }
}
