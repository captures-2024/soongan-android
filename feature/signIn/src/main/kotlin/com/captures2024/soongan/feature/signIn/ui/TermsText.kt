package com.captures2024.soongan.feature.signIn.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleSpanStyle
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signIn.R

@Composable
internal fun TermsText(
    onClickTermsOfUse: () -> Unit,
    onClickPrivacyPolicy: () -> Unit
) {
    val text = buildAnnotatedString {
        withStyle(
            style = NonScaleSpanStyle(
                fontSize = 11.sp,
                color = Color.White
            )
        ) {
            append(stringResource(id = R.string.terms_text_first))
        }
        pushStringAnnotation(tag = "termsOfUse", annotation = "termsOfUse")
        withStyle(
            style = NonScaleSpanStyle(
                fontSize = 11.sp,
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(id = R.string.terms_of_use))
        }
        pop()
        withStyle(
            style = NonScaleSpanStyle(
                fontSize = 11.sp,
                color = Color.White
            )
        ) {
            append(stringResource(id = R.string.terms_text_second))
        }
        pushStringAnnotation(tag = "privacyPolicy", annotation = "privacyPolicy")
        withStyle(
            style = NonScaleSpanStyle(
                fontSize = 11.sp,
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(id = R.string.privacy_policy))
        }
        pop()
        withStyle(
            style = NonScaleSpanStyle(
                fontSize = 11.sp,
                color = Color.White
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