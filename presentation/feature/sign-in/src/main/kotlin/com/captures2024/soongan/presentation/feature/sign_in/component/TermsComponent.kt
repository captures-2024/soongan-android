package com.captures2024.soongan.presentation.feature.sign_in.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.sign_in.R

@Composable
internal fun TermsComponent(
    modifier: Modifier = Modifier,
    onClickTerms: () -> Unit,
    onClickPrivacyPolicy: () -> Unit,
) {
    val firstContent = stringResource(R.string.terms_first_content)
    val secondContent = stringResource(R.string.terms_second_content)
    val thirdContent = stringResource(R.string.terms_third_content)

    val firstClickable = stringResource(R.string.terms_first_clickable_content)
    val firstStartIndex = firstContent.length
    val firstEndIndex = firstStartIndex + firstClickable.length

    val secondClickable = stringResource(R.string.terms_second_clickable_content)
    val secondStartIndex = firstEndIndex + secondContent.length
    val secondEndIndex = secondStartIndex + secondClickable.length

    val defaultStyle = getSGNonScaleSpanStyle(
        color = SGColor.primaryA,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = SGTypography.pretendard,
    )

    val underlineStyle = defaultStyle.copy(
        textDecoration = TextDecoration.Underline,
    )

    val content = buildAnnotatedString {
        withStyle(style = defaultStyle) {
            append(firstContent)
        }

        withStyle(style = underlineStyle) {
            append(firstClickable)
        }

        withStyle(style = defaultStyle) {
            append(secondContent)
        }

        withStyle(style = underlineStyle) {
            append(secondClickable)
        }

        withStyle(style = defaultStyle) {
            append(thirdContent)
        }

        addLink(
            clickable = LinkAnnotation.Clickable(
                tag = stringResource(R.string.terms_first_clickable_tag),
                styles = TextLinkStyles(style = underlineStyle),
                linkInteractionListener = { onClickTerms() },
            ),
            start = firstStartIndex,
            end = firstEndIndex,
        )

        addLink(
            clickable = LinkAnnotation.Clickable(
                tag = stringResource(R.string.terms_second_clickable_tag),
                styles = TextLinkStyles(style = underlineStyle),
                linkInteractionListener = { onClickPrivacyPolicy() },
            ),
            start = secondStartIndex,
            end = secondEndIndex,
        )
    }

    SGText(
        annotatedString = content,
        modifier = modifier,
    )
}

@DevicePreviews
@Composable
private fun PreviewTermsComponent() {
    SGBackground {
        TermsComponent(
            onClickTerms = {},
            onClickPrivacyPolicy = {},
        )
    }
}
