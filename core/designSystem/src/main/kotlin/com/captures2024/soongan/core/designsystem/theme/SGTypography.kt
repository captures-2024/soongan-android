package com.captures2024.soongan.core.designsystem.theme

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
import com.captures2024.soongan.core.design.R

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
    val nanumSquareNeo: FontFamily = FontFamily(
        Font(R.font.nanum_square_neo_bold, FontWeight.Bold),
        Font(R.font.nanum_square_neo_extrabold, FontWeight.ExtraBold),
        Font(R.font.nanum_square_neo_heavy, FontWeight.W900),
        Font(R.font.nanum_square_neo_light, FontWeight.Light),
        Font(R.font.nanum_square_neo_regular, FontWeight.Normal),
    )

    @Stable
    val poppins: FontFamily = FontFamily(
        Font(R.font.poppins_black, FontWeight.Black),
        Font(R.font.poppins_black_italic, FontWeight.Black, style = FontStyle.Italic),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_bold_italic, FontWeight.Bold, style = FontStyle.Italic),
        Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
        Font(R.font.poppins_extra_bold_italic, FontWeight.ExtraBold, style = FontStyle.Italic),
        Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
        Font(R.font.poppins_extra_light_italic, FontWeight.ExtraLight, style = FontStyle.Italic),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_italic, FontWeight.Normal, style = FontStyle.Italic),
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_light_italic, FontWeight.Light, style = FontStyle.Italic),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_medium_italic, FontWeight.Medium, style = FontStyle.Italic),
        Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
        Font(R.font.poppins_extra_bold_italic, FontWeight.SemiBold, style = FontStyle.Italic),
        Font(R.font.poppins_thin, FontWeight.Thin),
        Font(R.font.poppins_thin_italic, FontWeight.Thin, style = FontStyle.Italic),
    )

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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

    @Deprecated("피그마에 타이포 제대로 적용된거 없어서 쓰지마세요")
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
    SoonGanTheme {
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
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h2(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h3(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h4(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h5(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h6(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p2(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p3(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.small(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTypoNanumSquareNeo() {
    SoonGanTheme {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h1(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h2(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h3(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h4(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h5(
                    fontFamily = SGTypography.pretendard,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h6(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p2(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p3(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.small(
                    fontFamily = SGTypography.nanumSquareNeo,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTypoPoppins() {
    SoonGanTheme {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h1(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h2(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h3(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h4(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h5(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.h6(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p2(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.p3(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )

            Text(
                text = "맥도날드 감자튀김",
                style = SGTypography.small(
                    fontFamily = SGTypography.poppins,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}
