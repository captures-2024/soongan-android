package com.captures2024.soongan.feature.home.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.feature.home.navigation.ReportRouteNavHost
import com.captures2024.soongan.feature.home.state.ReportRouteState
import com.captures2024.soongan.feature.home.state.rememberReportRouteState
import com.captures2024.soongan.feature.home.ui.post.report.component.SheetTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReportRoute(
    targetId: Long,
    targetType: ReportTargetType,
    closeSheet: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val reportRouteState: ReportRouteState =
        rememberReportRouteState(targetId = targetId, targetType = targetType)

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = closeSheet,
        sheetState = reportRouteState.sheetState,
        containerColor = SGColor.white
    ) {
        Column {
            SheetTopBar(
                title = "신고",
                hasBackIcon = reportRouteState.isCheckRoute,
                onBackPressed = reportRouteState::popBackStack
            )

            ReportRouteNavHost(
                reportRouteState = reportRouteState,
                modifier = Modifier.fillMaxWidth(),
                closeSheet = closeSheet
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PostReportBottomSheetDialogPreview() {
    ReportRoute(
        targetId = 0,
        targetType = ReportTargetType.WEEKLY_POST,
        closeSheet = {}
    )
}