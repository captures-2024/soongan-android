package com.captures2024.soongan.presentation.feature.main.post.component.report

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.navigator.screen.main.report.CheckReportReasonNavigator
import com.captures2024.soongan.core.navigator.screen.main.report.DoneReportReasonNavigator
import com.captures2024.soongan.core.navigator.screen.main.report.SelectReportReasonNavigator
import com.captures2024.soongan.core.navigator.screen.main.report.navigateToCheckReportReason
import com.captures2024.soongan.core.navigator.screen.main.report.navigateToDoneReportReason
import com.captures2024.soongan.presentation.feature.main.post.route.CheckReportReasonRoute
import com.captures2024.soongan.presentation.feature.main.post.route.DoneReportReasonRoute
import com.captures2024.soongan.presentation.feature.main.post.route.SelectReportReasonRoute
import com.captures2024.soongan.presentation.viewmodel.main.post.PostReportViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReportBottomSheet(
    id: Long,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onDoneReport: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val postReportViewModel = hiltViewModel<PostReportViewModel>()

    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    var isAvailableBack by remember { mutableStateOf(true) }

    val navigateToBack: () -> Unit = {
        if (isAvailableBack) {
            isAvailableBack = false
            navController.navigateUp()

            scope.launch {
                delay(500L)
                isAvailableBack = true
            }
        }
    }

    LaunchedEffect(id) {
        postReportViewModel.intent(PostReportViewModel.Intent.InitId(id))
    }

    ModalBottomSheet(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = SGColor.white,
        dragHandle = @Composable {
            ReportBottomSheetDragHandleComponent()
        },
    ) {
        ReportBottomSheetTopBarComponent(
            onClickBack = navigateToBack,
        )

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = SelectReportReasonNavigator,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            composable<SelectReportReasonNavigator> {
                SelectReportReasonRoute(
                    viewModel = postReportViewModel,
                    navigateToCheckReportType = navController::navigateToCheckReportReason,
                )
            }

            composable<CheckReportReasonNavigator> {
                CheckReportReasonRoute(
                    viewModel = postReportViewModel,
                    navigateToDoneReportReason = navController::navigateToDoneReportReason,
                )
            }

            composable<DoneReportReasonNavigator> {
                DoneReportReasonRoute(
                    viewModel = postReportViewModel,
                    navigateToHidePost = {
                        onDismissRequest()
                        onDoneReport()
                    },
                )
            }
        }
    }
}
