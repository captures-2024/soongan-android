package com.captures2024.soongan.feature.home.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.feature.home.navigation.ReportRouteNavHost
import com.captures2024.soongan.feature.home.state.ReportRouteState
import com.captures2024.soongan.feature.home.state.rememberReportRouteState
import com.captures2024.soongan.feature.home.ui.post.report.component.CustomDragHandle
import com.captures2024.soongan.feature.home.ui.post.report.component.ReportTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReportRoute(
    reportRouteState: ReportRouteState,
    closeSheet: () -> Unit,
    reportPost: () -> Unit,
    modifier: Modifier = Modifier,
) {
    reportRouteState.ManageDisableDismissState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { !reportRouteState.disableDismissState.value }
    )

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { closeSheet() },
        sheetState = sheetState,
        containerColor = SGColor.white,
        dragHandle = @Composable { CustomDragHandle() }
    ) {
        Column {
            ReportTopBar(
                hasBackIcon = reportRouteState.isCheckRoute,
                onBackPressed = reportRouteState::popBackStack
            )

            ReportRouteNavHost(
                reportRouteState = reportRouteState,
                modifier = Modifier.fillMaxWidth(),
                reportPost = reportPost
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun PostReportBottomSheetDialogPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        density = LocalDensity.current,
        initialValue = SheetValue.Expanded,
        confirmValueChange = { true },
    )

    ReportRoute(
        reportRouteState = rememberReportRouteState(0, ReportTargetType.WEEKLY_POST),
        closeSheet = {},
        reportPost = {}
    )
}