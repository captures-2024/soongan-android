package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.utils.extension.getTextId

@Composable
internal fun SelectReportReasonScreen(
    modifier: Modifier = Modifier,
    onClickReportType: (ReportType) -> Unit,
) {
    val lastIdx = ReportType.entries.lastIndex

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 12.dp),
    ) {
        ReportType.entries.forEachIndexed { idx, type ->
            PostReportDefaultBody(
                text = stringResource(id = type.getTextId()),
                isVisibleDivider = (idx != lastIdx),
                onClick = { onClickReportType(type) },
            )
        }
    }
}

@Composable
private fun PostReportDefaultBody(
    text: String,
    modifier: Modifier = Modifier,
    isVisibleDivider: Boolean = true,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp),
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
            ),
            modifier = Modifier.padding(vertical = 20.dp),
        )
    }
    if (isVisibleDivider) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewSelectReportReasonScreen() {
    SGTheme {
        SelectReportReasonScreen(
            onClickReportType = {},
        )
    }
}
