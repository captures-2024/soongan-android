package com.captures2024.soongan.feature.home.ui.post.report

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
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.SoonGanButton
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleSpanStyle
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
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
    val commonTextStyle = SGNonScaleSpanStyle(
        color = SGColor.primaryA,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = NanumSquareNeoFontFamily,
        letterSpacing = (-5).em,
    )

    val checkMassage =
        buildAnnotatedString {
            pushStyle(ParagraphStyle(lineHeight = 24.sp))
            withStyle(style = commonTextStyle) {
                append(stringResource(id = R.string.report_prefix_text))
                append("\n")
            }
            withStyle(style = commonTextStyle.copy(fontWeight = FontWeight.Bold)) {
                append(stringResource(id = reportType.getTextId()))
            }
            withStyle(style = commonTextStyle) {
                append(stringResource(id = R.string.report_suffix_text))
            }
        }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp, bottom = 28.dp)
    ) {
        Text(
            text = checkMassage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
        HeightSpacer(40.dp)

        SoonGanButton(
            onClick = onClickSubmit,
            modifier = Modifier.fillMaxWidth()
        ) {
            SGText(
                text = stringResource(id = R.string.report_submit_text),
                SGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = PretendardFontFamily,
                    letterSpacing = (-5).em
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