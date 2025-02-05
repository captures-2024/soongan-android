package com.captures2024.soongan.feature.home.ui.post.report

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
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.feature.home.utils.getTextId

@Composable
internal fun ReportIdleScreen(
    modifier: Modifier = Modifier,
    navigateToCheck: (reportType: ReportType) -> Unit,
) {
    val lastIdx = ReportType.entries.lastIndex

    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        HeightSpacer(16.dp)
        ReportType.entries.forEachIndexed { idx, type ->
            PostReportDefaultBody(
                text = stringResource(id = type.getTextId()),
                isVisibleDivider = (idx != lastIdx)
            ) {
                navigateToCheck(type)
            }
        }
        HeightSpacer(16.dp)
    }
}

@Composable
private fun PostReportDefaultBody(
    text: String,
    isVisibleDivider: Boolean = true,
    onClick: () -> Unit = {},
) {
    HeightSpacer(16.dp)
    Row {
        WidthSpacer(20.dp)
        SGText(
            text = text,
            style = SGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                fontFamily = NanumSquareNeoFontFamily,
            ),
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
    HeightSpacer(16.dp)
    if (isVisibleDivider) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = SGColor.primaryA.copy(alpha = 0.3f),
        )
    }
}

@DevicePreviews
@Composable
private fun PostReportScreenPreview() {
    ReportIdleScreen {}
}