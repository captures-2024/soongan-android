package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun ReportReasonScreen(
    reason: String,
    modifier: Modifier = Modifier,
    onReasonChanged: (String) -> Unit = {},
    onClickSubmit: () -> Unit = {},
) {
    val isEnabled = reason.isNotEmpty()

    Column(modifier = modifier) {
        ReportReasonInput(
            text = reason,
            onTextChange = onReasonChanged
        )
        HeightSpacer(48.dp)
        ReportButton(
            onClickSubmit = onClickSubmit,
            enabled = isEnabled
        )
    }
}

@Composable
private fun ReportReasonInput(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SGColor.white,
                shape = RoundedCornerShape(8.dp),
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = SGColor.primaryA.copy(alpha = 0.15f)
                ),
                shape = RoundedCornerShape(8.dp),
            )
            .height(160.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            value = text,
            onValueChange = { newText ->
                if (newText.length <= 200) {
                    onTextChange(newText)
                }
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = SGColor.primaryA,
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SGColor.white,
                            shape = RoundedCornerShape(8.dp),
                        )
                ) {
                    if (text.isEmpty()) {
                        SGText(
                            text = stringResource(R.string.report_reason_input_text),
                            style = SGNonScaleTextStyle(
                                color = SGColor.primaryA.copy(alpha = 0.3f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                fontFamily = NanumSquareNeoFontFamily,
                                letterSpacing = (-5).em,
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )

        Text(
            text = "${text.length}/200",
            fontSize = 12.sp,
            color = when (text.length < 200) {
                true -> SGColor.primaryA
                false -> SGColor.negative
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd),
        )
    }
}

@Composable
private fun ReportButton(
    modifier: Modifier = Modifier,
    onClickSubmit: () -> Unit,
    enabled: Boolean,
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(
                color = when (enabled) {
                    true -> SGColor.primaryA
                    false -> SGColor.tempPrimaryC
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(enabled = enabled) {
                onClickSubmit()
            },
        contentAlignment = Alignment.Center
    ) {
        NonScaleText(
            text = stringResource(id = R.string.report_submit_text),
            color = SGColor.primaryB,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            fontFamily = PretendardFontFamily,
        )
    }
}

@DevicePreviews
@Composable
private fun ReportReasonScreenPreview() {
    ReportReasonScreen(reason = "")
}