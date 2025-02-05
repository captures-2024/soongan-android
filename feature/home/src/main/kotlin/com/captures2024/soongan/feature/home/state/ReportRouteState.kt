package com.captures2024.soongan.feature.home.state

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

@Composable
internal fun rememberReportRouteState(
    targetId: Long,
    targetType: ReportTargetType,
    navController: NavHostController = rememberNavController(),
): ReportRouteState = remember(Unit) {
    ReportRouteState(
        targetId = targetId,
        targetType = targetType,
        navController = navController,
    )
}


@Stable
internal class ReportRouteState(
    val targetId: Long,
    val targetType: ReportTargetType,
    val navController: NavHostController,
) {
    val isCheckRoute: Boolean
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination?.hasRoute(
            ReportNavigator.Check::class
        ) ?: false

    fun popBackStack() = navController.popBackStack()

    fun navigateToReportCheck(reportType: ReportType) =
        navController.navigate(ReportNavigator.Check(reportType = reportType.name))

    fun navigateToReportDone(hasExtraMessage: Boolean) =
        navController.navigate(ReportNavigator.Done(hasExtraMessage = hasExtraMessage))
}
