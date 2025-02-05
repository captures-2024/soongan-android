package com.captures2024.soongan.feature.home.state

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.core.navigator.screen.main.report.ReportNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rememberReportRouteState(
    targetId: Long,
    targetType: ReportTargetType,
    navController: NavHostController = rememberNavController(),
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
): ReportRouteState = remember(Unit) {
    ReportRouteState(
        targetId = targetId,
        targetType = targetType,
        navController = navController,
        sheetState = sheetState
    )
}


@Stable
internal class ReportRouteState
@OptIn(ExperimentalMaterial3Api::class)
constructor(
    val targetId: Long,
    val targetType: ReportTargetType,
    val navController: NavHostController,
    val sheetState: SheetState,
) {
    val isCheckRoute: Boolean
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination?.hasRoute(
            ReportNavigator.Check::class
        ) ?: false

    fun popBackStack() = navController.popBackStack()

    fun navigateToReportCheck(reportType: ReportType) {
        navController.navigate(ReportNavigator.Check(reportType = reportType.name))
    }

    fun navigateToReportDone() = navController.navigate(ReportNavigator.Done)
}
