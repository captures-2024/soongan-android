package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun DoneReportReasonScreen(
    hasExtraMessage: Boolean,
    modifier: Modifier = Modifier,
    onClickConfirm: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 28.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 40.dp,
                ),
        ) {
            SGText(
                text = stringResource(R.string.report_done_message),
                getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            if (hasExtraMessage) {
                SGText(
                    text = stringResource(R.string.report_done_extra_message),
                    getSGNonScaleTextStyle(
                        color = SGColor.Grayscale.black100,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.pretendard,
                        letterSpacing = (-5).em,
                        textDecoration = TextDecoration.Underline,
                    ),
                )
            }

            SGText(
                text = stringResource(R.string.report_done_last_message),
                getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }

        SGTextButtonType2(
            text = stringResource(R.string.button_confirm),
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickConfirm,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewDoneReportReasonScreen_Default() {
    SGTheme {
        DoneReportReasonScreen(
            hasExtraMessage = false,
            onClickConfirm = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewDoneReportReasonScreen_Extra() {
    SGTheme {
        DoneReportReasonScreen(
            hasExtraMessage = true,
            onClickConfirm = {},
        )
    }
}
