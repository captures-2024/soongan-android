package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleSpanStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.R
import com.captures2024.soongan.presentation.feature.main.post.utils.extension.getTextId
import com.captures2024.soongan.presentation.viewmodel.main.post.PostReportViewModel

@Composable
internal fun ReportReasonScreen(
    state: PostReportViewModel.State,
    modifier: Modifier = Modifier,
    onClickSubmit: () -> Unit,
) {
    val commonTextStyle = getSGNonScaleSpanStyle(
        color = SGColor.Grayscale.black100,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = SGTypography.pretendard,
        letterSpacing = (-5).em,
    )

    val checkMassage = buildAnnotatedString {
        pushStyle(ParagraphStyle(lineHeight = 24.sp))
        withStyle(style = commonTextStyle) {
            append(stringResource(R.string.report_reason_check_message_prefix))
            append("\n")
        }
        withStyle(
            style = commonTextStyle.copy(
                fontWeight = FontWeight.Bold,
            ),
        ) {
            append(stringResource(id = state.selectedReportType.getTextId()))
        }
        withStyle(style = commonTextStyle) {
            append(stringResource(R.string.report_reason_check_message_suffix))
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .padding(
                top = 40.dp,
                bottom = 28.dp,
            ),
    ) {
        Text(
            text = checkMassage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )

        HeightSpacer(40.dp)

        SGTextButtonType2(
            text = stringResource(R.string.button_confirm),
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickSubmit,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewReportReasonScreen() {
    SGTheme {
        ReportReasonScreen(
            state = PostReportViewModel.State(
                id = -1L,
                selectedReportType = ReportType.INAPPROPRIATE_PHOTO_OR_BEHAVIOR,
                reason = "",
                maxReasonLength = 200,
            ),
            onClickSubmit = {},
        )
    }
}
