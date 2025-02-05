package com.captures2024.soongan.feature.home.navigation

import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.captures2024.soongan.core.navigator.screen.main.report.ReportNavigator
import com.captures2024.soongan.feature.home.state.ReportRouteState
import com.captures2024.soongan.feature.home.ui.post.report.ReportCheckScreen
import com.captures2024.soongan.feature.home.ui.post.report.ReportDoneScreen
import com.captures2024.soongan.feature.home.ui.post.report.ReportIdleScreen

@Composable
internal fun ReportRouteNavHost(
    reportRouteState: ReportRouteState,
    modifier: Modifier = Modifier,
    closeSheet: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = reportRouteState.navController,
        startDestination = ReportNavigator.Idle,
        sizeTransform = {
            SizeTransform(
                clip = true,
                sizeAnimationSpec = { _, _ -> tween(durationMillis = 300) })
        }
    ) {
        composable<ReportNavigator.Idle> {
            ReportIdleScreen(
                navigateToCheck = reportRouteState::navigateToReportCheck
            )
        }

        composable<ReportNavigator.Check> {
            ReportCheckScreen(
                reportRouteState = reportRouteState,
                navigateToDone = reportRouteState::navigateToReportDone
            )
        }

        composable<ReportNavigator.Done> {
            ReportDoneScreen(
                targetType = reportRouteState.targetType,
                onClickConfirm = closeSheet
            )
        }
    }
}