package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
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

        SGTextButtonType2(
            text = stringResource(R.string.report_reason_submit_text),
            modifier = Modifier.fillMaxWidth(),
            enabled = isEnabled,
            onClick = onClickSubmit,
        )
    }
}

@DevicePreviews
@Composable
private fun ReportReasonScreenPreview() {
    ReportReasonScreen(reason = "")
}