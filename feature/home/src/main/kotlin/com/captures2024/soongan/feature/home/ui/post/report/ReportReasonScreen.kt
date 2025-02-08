package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.SoonGanButton
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.ui.post.report.component.ReportReasonTextField

@Composable
internal fun ReportReasonScreen(
    reason: String,
    modifier: Modifier = Modifier,
    onReasonChanged: (String) -> Unit = {},
    onClickSubmit: () -> Unit = {},
) {
    val isEnabled = reason.isNotEmpty()

    Column(modifier = modifier.padding(horizontal = 20.dp).padding(top = 40.dp, bottom = 28.dp)) {
        ReportReasonTextField(
            text = reason,
            onTextChange = onReasonChanged
        )
        HeightSpacer(48.dp)
        SoonGanButton(
            onClick = onClickSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = isEnabled
        ) {
            SGText(
                text = stringResource(R.string.report_reason_submit_text),
                style = getSGNonScaleTextStyle(
                    color = if(isEnabled) SGColor.primaryA else SGColor.white,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = NanumSquareNeoFontFamily,
                    letterSpacing = 0.sp
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun ReportReasonScreenPreview() {
    ReportReasonScreen(reason = "")
}