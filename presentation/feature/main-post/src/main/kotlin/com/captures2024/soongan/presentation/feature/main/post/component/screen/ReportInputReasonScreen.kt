package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.feature.main.post.component.report.ReportReasonTextFieldComponent
import com.captures2024.soongan.presentation.viewmodel.main.post.PostReportViewModel

@Composable
internal fun ReportInputReasonScreen(
    state: PostReportViewModel.State,
    modifier: Modifier = Modifier,
    onReasonValueChanged: (String) -> Unit,
    onClickSubmit: () -> Unit,
) {
    val isEnabled = state.reason.isNotEmpty()

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(
                top = 40.dp,
                bottom = 28.dp,
            ),
    ) {
        ReportReasonTextFieldComponent(
            text = state.reason,
            onValueChanged = onReasonValueChanged,
            maxLength = state.maxReasonLength,
        )

        HeightSpacer(48.dp)

        SGTextButtonType2(
            text = stringResource(R.string.button_confirm),
            modifier = Modifier.fillMaxWidth(),
            enabled = isEnabled,
            onClick = onClickSubmit,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewReportInputReasonScreen() {
    SGTheme {
        ReportInputReasonScreen(
            state = PostReportViewModel.State(
                id = -1L,
                selectedReportType = ReportType.OTHER,
                reason = "",
                maxReasonLength = 200,
            ),
            onReasonValueChanged = {},
            onClickSubmit = {},
        )
    }
}
