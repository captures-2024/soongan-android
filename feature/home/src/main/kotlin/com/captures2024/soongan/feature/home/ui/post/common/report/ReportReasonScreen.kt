package com.captures2024.soongan.feature.home.ui.post.common.report

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.Negative
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.theme.PrimaryC
import com.captures2024.soongan.feature.home.R

@Composable
internal fun ReportReasonScreen(
    modifier: Modifier = Modifier,
    onClickSubmit: (String) -> Unit
) {
    var reason by remember { mutableStateOf("") }
    val isEnabled = reason.isNotEmpty()

    Column(modifier = modifier) {
        ReportReasonInput(
            text = reason,
            onTextChange = { reason = it }
        )
        HeightSpacer(48.dp)
        ReportButton(
            onClickSubmit = { onClickSubmit(reason) },
            enabled = isEnabled
        )
    }
}

@Composable
private fun ReportReasonInput(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryB, RoundedCornerShape(8.dp))
            .height(160.dp)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            value = text,
            onValueChange = { newText ->
                if (newText.length <= 200) onTextChange(newText)
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = PrimaryA
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryB, shape = RoundedCornerShape(8.dp))
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.report_reason_input_text),
                            fontSize = 16.sp,
                            color = PrimaryA.copy(alpha = 0.3f)
                        )
                    }
                    innerTextField()
                }
            }
        )

        Text(
            text = "${text.length}/200",
            fontSize = 12.sp,
            color = if (text.length < 200) PrimaryA else Negative,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun ReportButton(
    modifier: Modifier = Modifier,
    onClickSubmit: () -> Unit,
    enabled: Boolean
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(
                color = if (enabled) PrimaryA else PrimaryC,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(enabled = enabled) {
                onClickSubmit()

            },
        contentAlignment = Alignment.Center
    ) {
        NonScaleText(
            text = stringResource(id = R.string.report_submit_text),
            color = PrimaryB,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            fontFamily = PretendardFontFamily
        )
    }
}

@Preview
@Composable
private fun ReportReasonScreenPreview() {
    ReportReasonScreen { }
}