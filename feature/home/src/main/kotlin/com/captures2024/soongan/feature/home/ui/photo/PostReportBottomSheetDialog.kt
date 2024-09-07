package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.state.PhotoDetailModalState
import com.captures2024.soongan.feature.home.utils.ReportType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostReportBottomSheetDialog(
    modifier: Modifier = Modifier,
    reportState: PhotoDetailModalState.Open.ReportOpen,
    onClickReport: (ReportType) -> Unit,
    closeSheet: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    
    ModalBottomSheet(
        modifier = modifier
            .height(
                when (reportState.reportType) {
                    ReportType.NONE, ReportType.FINISH -> 448.dp
                    else -> 263.dp
                }
            ),
        onDismissRequest = closeSheet,
        sheetState = sheetState,
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            topBar = @Composable {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NonScaleText(
                        text = stringResource(id = R.string.report_title_text),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        fontFamily = NanumSquareNeoFontFamily
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(
                        color = Color(0x4D252525),
                        thickness = 1.dp
                    )
                }
            },
        ) { paddingValues ->
            when (reportState.reportType) {
                ReportType.NONE -> PostReportDefaultScreen(
                    modifier = Modifier.padding(paddingValues),
                    onClickReport = onClickReport
                )
                ReportType.FINISH -> PostReportFinishScreen(
                    onClickConfirm = closeSheet
                )
                else -> PostReportDetailScreen(
                    modifier = Modifier.padding(paddingValues),
                    reportType = reportState.reportType,
                    onClickSubmit = { onClickReport(ReportType.FINISH) }
                )
            }
        }
    }
}



@DevicePreviews
@Composable
private fun PostReportBottomSheetDialogPreview() {
    PostReportBottomSheetDialog(
        reportState = PhotoDetailModalState.Open.ReportOpen(),
        onClickReport = {},
        closeSheet = {}
    )
}