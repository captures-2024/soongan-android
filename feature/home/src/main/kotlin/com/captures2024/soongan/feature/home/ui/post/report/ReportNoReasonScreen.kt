package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.SGTextStyle
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.utils.getTextId

@Composable
internal fun ReportNoReasonScreen(
    reportType: ReportType,
    modifier: Modifier = Modifier,
    onClickSubmit: () -> Unit = {},
) {
    val detailReport = stringResource(id = reportType.getTextId())
    val text =
        "${stringResource(id = R.string.report_prefix_text)}\n$detailReport\n${stringResource(id = R.string.report_suffix_text)}"

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp, bottom = 28.dp)
    ) {
        SGText(
            text = text,
            SGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                fontFamily = NanumSquareNeoFontFamily,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
        HeightSpacer(40.dp)
        Box(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .background(
                    color = SGColor.primaryA,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable(onClick = onClickSubmit),
            contentAlignment = Alignment.Center
        ) {
            SGText(
                text = stringResource(id = R.string.report_submit_text),
                SGTextStyle(
                    color = SGColor.primaryB,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    fontFamily = PretendardFontFamily
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PostReportDetailScreenPreview() {
    ReportNoReasonScreen(reportType = ReportType.INAPPROPRIATE_PHOTO_OR_BEHAVIOR)
}