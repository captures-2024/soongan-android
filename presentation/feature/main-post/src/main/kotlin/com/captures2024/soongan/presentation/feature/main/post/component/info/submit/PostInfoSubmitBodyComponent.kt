package com.captures2024.soongan.presentation.feature.main.post.component.info.submit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoSubmitBodyComponent(
    title: String,
    content: String,
    isChecked: Boolean,
    onClickCheckBox: () -> Unit,
    onClickTerms: () -> Unit,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit,
) {
    val content = StringBuilder()
        .append(title)
        .append("\n")
        .append(content)
        .toString()

    val checkBoxContentDefaultStyle = getSGNonScaleSpanStyle(
        color = SGColor.primaryA,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = SGTypography.pretendard,
        letterSpacing = (-5).em,
    )

    val prefixCheckBoxContent = stringResource(R.string.post_info_registration_submit_prefix_check_box_content)
    val firstCheckBoxContent = stringResource(R.string.post_info_registration_submit_first_box_content)
    val secondCheckBoxContent = stringResource(R.string.post_info_registration_submit_second_box_content)
    val thirdCheckBoxContent = stringResource(R.string.post_info_registration_submit_third_box_content)
    val clickableCheckBoxContent = stringResource(R.string.post_info_registration_submit_clickable_box_content)
    val clickableTag = stringResource(R.string.post_info_registration_submit_clickable_box_tag)

    val firstStartIndex = prefixCheckBoxContent.length + firstCheckBoxContent.length
    val firstEndIndex = firstStartIndex + clickableCheckBoxContent.length

    val checkBoxContent = buildAnnotatedString {
        withStyle(style = ParagraphStyle(lineHeight = 16.sp)) {
            withStyle(
                style = checkBoxContentDefaultStyle.copy(color = SGColor.Main.primary),
            ) {
                append(prefixCheckBoxContent)
            }

            withStyle(style = checkBoxContentDefaultStyle) {
                append(firstCheckBoxContent)
            }

            withStyle(
                style = checkBoxContentDefaultStyle.copy(
                    textDecoration = TextDecoration.Underline,
                ),
            ) {
                append(clickableCheckBoxContent)
            }

            withStyle(style = checkBoxContentDefaultStyle) {
                append(secondCheckBoxContent)
            }

            withStyle(
                style = checkBoxContentDefaultStyle.copy(
                    color = SGColor.transparent,
                ),
            ) {
                append(prefixCheckBoxContent)
            }

            withStyle(style = checkBoxContentDefaultStyle) {
                append(thirdCheckBoxContent)
            }

            addLink(
                clickable = LinkAnnotation.Clickable(
                    tag = clickableTag,
                    styles = TextLinkStyles(
                        style = checkBoxContentDefaultStyle.copy(
                            textDecoration = TextDecoration.Underline,
                        ),
                    ),
                    linkInteractionListener = { onClickTerms() },
                ),
                start = firstStartIndex,
                end = firstEndIndex,
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Icon(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = stringResource(R.string.back_description),
                    modifier = Modifier.clickable(onClick = onClickCancel),
                    tint = SGColor.Grayscale.black100,
                )
            }

            SGText(
                text = stringResource(R.string.post_info_registration_submit_body_title),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }

        HeightSpacer(12.dp)

        HorizontalDivider(
            color = SGColor.buttonDisableGray,
            thickness = 1.dp,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .padding(
                    top = 14.dp,
                    bottom = 28.dp,
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                SGText(
                    text = content,
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.pretendard,
                        letterSpacing = (-5).em,
                    ),
                )

                HeightSpacer(18.dp)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(
                            id = when (isChecked) {
                                true -> com.captures2024.soongan.presentation.designsystem.ui.R.drawable.check_box_selected
                                false -> com.captures2024.soongan.presentation.designsystem.ui.R.drawable.check_box_unselected
                            },
                        ),
                        contentDescription = "checkBox",
                        modifier = Modifier
                            .size(
                                width = 24.dp,
                                height = 24.dp,
                            )
                            .clickable(onClick = onClickCheckBox),
                    )

                    WidthSpacer(16.dp)

                    SGText(
                        annotatedString = checkBoxContent,
                    )
                }
            }

            HeightSpacer(16.dp)

            SGTextButtonType2(
                text = stringResource(R.string.post_info_registration_submit_body_button_content),
                enabled = isChecked,
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickConfirm,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoSubmitBodyComponent_Unchecked() {
    SGTheme {
        PostInfoSubmitBodyComponent(
            title = "test",
            content = "content",
            isChecked = false,
            onClickCheckBox = {},
            onClickTerms = {},
            onClickConfirm = {},
            onClickCancel = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoSubmitBodyComponent_Checked() {
    SGTheme {
        PostInfoSubmitBodyComponent(
            title = "test",
            content = "content",
            isChecked = true,
            onClickCheckBox = {},
            onClickTerms = {},
            onClickConfirm = {},
            onClickCancel = {},
        )
    }
}
