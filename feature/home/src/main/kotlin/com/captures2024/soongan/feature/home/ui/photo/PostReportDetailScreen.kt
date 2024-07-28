package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.state.ReportType

@Composable
internal fun PostReportDetailScreen(
    modifier: Modifier = Modifier,
    reportType: ReportType,
    onClickSubmit: () -> Unit
) {
    val detailReport = stringResource(
        id = when (reportType) {
            ReportType.INAPPROPRIATE_IMAGE_OR_LANGUAGE -> R.string.report_inappropriate_image_or_language_text
            ReportType.HATE_SPEECH -> R.string.report_hate_speech_text
            ReportType.COPYRIGHT_INFRINGEMENT -> R.string.report_copyright_infringement_text
            ReportType.SPAM -> R.string.report_spam_text
            ReportType.PROMOTION -> R.string.report_promotion_text
            ReportType.OTHER -> R.string.report_other_text
            ReportType.NONE, ReportType.FINISH -> R.string.report_empty_text
        }
    )
    val text = "${stringResource(id = R.string.report_prefix_text)} $detailReport${stringResource(id = R.string.report_suffix_text)}"

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp, bottom = 28.dp)
    ) {
        NonScaleText(
            text = text,
            color = PrimaryA,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            fontFamily = NanumSquareNeoFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(
                    color = PrimaryA,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
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
}

@DevicePreviews
@Composable
private fun PostReportDetailScreenPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        PostReportDetailScreen(
            reportType = ReportType.INAPPROPRIATE_IMAGE_OR_LANGUAGE
        ) {

        }
    }
}