package com.captures2024.soongan.feature.home.ui.post.common.report

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PretendardFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R

@Composable
internal fun ReportCompleteScreen(
    modifier: Modifier = Modifier,
    onClickConfirm: () -> Unit
) {
    val text = stringResource(id = R.string.report_finish_text).trimIndent()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            NonScaleText(
                text = text,
                color = SGColor.primaryA,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                fontFamily = NanumSquareNeoFontFamily,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(
                    color = SGColor.primaryA,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    onClickConfirm()
                },
            contentAlignment = Alignment.Center
        ) {
            NonScaleText(
                text = stringResource(id = R.string.report_confirm_text),
                color = SGColor.primaryB,
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
private fun PostReportFinishScreenPreview() {
    Box(modifier = Modifier.background(SGColor.white)) {
        ReportCompleteScreen {

        }
    }
}