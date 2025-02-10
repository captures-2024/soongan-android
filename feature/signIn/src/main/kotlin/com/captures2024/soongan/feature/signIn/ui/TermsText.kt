package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signIn.R

@Composable
internal fun TermsText(
    onClickTermsOfUse: () -> Unit,
    onClickPrivacyPolicy: () -> Unit
) {
    val text = buildAnnotatedString {
        withStyle(
            style = getSGNonScaleSpanStyle(
                color = SGColor.primaryB,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SGTypography.pretendard,
            )
        ) {
            append(stringResource(id = R.string.terms_text_first))
        }
        pushStringAnnotation(tag = "termsOfUse", annotation = "termsOfUse")
        withStyle(
            style = getSGNonScaleSpanStyle(
                color = SGColor.primaryB,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SGTypography.pretendard,
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append(stringResource(id = R.string.terms_of_use))
        }
        pop()
        withStyle(
            style = getSGNonScaleSpanStyle(
                color = SGColor.primaryB,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SGTypography.pretendard,
            )
        ) {
            append(stringResource(id = R.string.terms_text_second))
        }
        pushStringAnnotation(tag = "privacyPolicy", annotation = "privacyPolicy")
        withStyle(
            style = getSGNonScaleSpanStyle(
                color = SGColor.primaryB,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SGTypography.pretendard,
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append(stringResource(id = R.string.privacy_policy))
        }
        pop()
        withStyle(
            style = getSGNonScaleSpanStyle(
                color = SGColor.primaryB,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SGTypography.pretendard,
            )
        ) {
            append(stringResource(id = R.string.terms_text_third))
        }
    }

    Box(contentAlignment = Alignment.Center) {
        ClickableText(
            text = text,
            onClick = { offset ->
                text.getStringAnnotations(
                    tag = "termsOfUse",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    onClickTermsOfUse()
                }
                text.getStringAnnotations(
                    tag = "privacyPolicy",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    onClickPrivacyPolicy()
                }
            }
        )
    }
}

@DevicePreviews
@Composable
private fun TermsTextPreview() {
    TermsText(
        onClickTermsOfUse = {},
        onClickPrivacyPolicy = {}
    )
}