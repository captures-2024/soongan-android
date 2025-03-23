package com.captures2024.soongan.feature.profile.ui.refute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldTypeLong
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.component.CustomTopBar

@Composable
internal fun RefuteScreen(
    isEnable: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    maxSize: Int,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickReport: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold (
        modifier = modifier.fillMaxSize(),
        topBar = @Composable {
            Column {
                CustomTopBar(
                    text = stringResource(R.string.refute_screen_title),
                    onBackPressed = onClickBack,
                )
                HorizontalDivider(color = SGColor.buttonDisableGray)
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.padding(20.dp)
            ) {
                SGTextButtonType2(
                    text = stringResource(R.string.refute_screen_button_content),
                    onClick = onClickReport,
                    enabled = isEnable,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

        },
        containerColor = SGColor.white,
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(
                        width = 114.dp,
                        height = 109.dp,
                    )
                    .background(
                        color = SGColor.tempPrimaryD,
                        shape = RectangleShape,
                    ),
            )

            SGText(
                annotatedString = buildAnnotatedString {
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SGTypography.nanumSquareNeo,
                            letterSpacing = 0.em,
                        ),
                    ) {
                        append(stringResource(R.string.refute_screen_content_1))
                    }
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SGTypography.nanumSquareNeo,
                            letterSpacing = 0.em,
                        ),
                    ) {
                        append(stringResource(R.string.refute_screen_content_2))
                    }
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SGTypography.nanumSquareNeo,
                            letterSpacing = 0.em,
                        ),
                    ) {
                        append(stringResource(R.string.refute_screen_content_3))
                        append(stringResource(R.string.refute_screen_content_4))
                        append(stringResource(R.string.refute_screen_content_5))
                        append(stringResource(R.string.refute_screen_content_6))
                        append(stringResource(R.string.refute_screen_content_7))
                    }
                    withStyle(
                        style = getSGNonScaleSpanStyle(
                            color = SGColor.primaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SGTypography.nanumSquareNeo,
                            letterSpacing = 0.em,
                            textDecoration = TextDecoration.Underline
                        ),
                    ) {
                        append(stringResource(R.string.refute_screen_content_8))
                        append(stringResource(R.string.refute_screen_content_9))
                    }
                }
            )

            SGTextFieldTypeLong(
                value = value,
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = onValueChange,
                hint = stringResource(R.string.refute_screen_hint),
                placeholderText = "${value.length}/$maxSize",
                placeholderStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                    textAlign = TextAlign.End,
                ),
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewRefuteScreen() {
    SGTheme {
        RefuteScreen(
            isEnable = false,
            value = "",
            onValueChange = {},
            maxSize = 1000,
            onClickBack = {},
            onClickReport = {},
        )
    }
}