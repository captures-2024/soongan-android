package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.state.ReportType

@Composable
internal fun PostReportDefaultScreen(
    modifier: Modifier = Modifier,
    onClickReport: (ReportType) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PostReportDefaultBody(text = "부적절한 사진 게시 및 언행") {
            onClickReport(ReportType.INAPPROPRIATE_IMAGE_OR_LANGUAGE)
        }
        PostReportDefaultBody(text = "욕설, 혐오, 비하 등이 포함된 표현") {
            onClickReport(ReportType.HATE_SPEECH)
        }
        PostReportDefaultBody(text = "도용, 초상권, 저작권 등 타인의 권리 침해") {
            onClickReport(ReportType.COPYRIGHT_INFRINGEMENT)
        }
        PostReportDefaultBody(text = "게시물 도배") {
            onClickReport(ReportType.SPAM)
        }
        PostReportDefaultBody(text = "홍보용 사진 혹은 댓글 게시") {
            onClickReport(ReportType.PROMOTION)
        }
        PostReportDefaultBody(
            text = "기타",
            isVisibleDivider = false
        ) {
            onClickReport(ReportType.OTHER)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun PostReportDefaultBody(
    text: String,
    isVisibleDivider: Boolean = true,
    onClick: () -> Unit = {}
) {
    Spacer(modifier = Modifier.height(16.dp))
    Row {
        Spacer(modifier = Modifier.width(20.dp))
        NonScaleText(
            text = text,
            color = PrimaryA,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 24.sp,
            fontFamily = NanumSquareNeoFontFamily,
            modifier = Modifier.clickable {
                onClick()
            }
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    if (isVisibleDivider) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = PrimaryA.copy(alpha = 0.3f),
        )
    }
}

@DevicePreviews
@Composable
private fun PostReportScreenPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        PostReportDefaultScreen {

        }
    }
}