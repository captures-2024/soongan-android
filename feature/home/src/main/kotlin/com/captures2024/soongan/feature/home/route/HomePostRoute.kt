package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel.Effect
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel.Intent
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState
import com.captures2024.soongan.feature.home.state.rememberReportRouteState
import com.captures2024.soongan.feature.home.ui.post.HomePostMenuBottomSheetDialog
import com.captures2024.soongan.feature.home.ui.post.HomePostScreen
import com.captures2024.soongan.feature.home.ui.post.comment.HomePostCommentBottomSheetDialog

@Composable
internal fun HomePostRoute(
    navigateToBack: () -> Unit,
    navigateToHomePostPhoto: (String) -> Unit,
    setReportedPostId: (postId: Long) -> Unit,
    homePostViewModel: HomePostViewModel = hiltViewModel(),
) {
    val uiState by homePostViewModel.state.collectAsStateWithLifecycle()

    val reportRouteState = rememberReportRouteState(
        targetId = uiState.postId,
        targetType = ReportTargetType.WEEKLY_POST,
    )

    LaunchedEffect(key1 = Unit) {
        homePostViewModel.sideEffect.collect { effect ->
            when (effect) {
                is Effect.NavigateToBack -> navigateToBack()

                is Effect.NavigateToHomePostPhoto -> navigateToHomePostPhoto(effect.url)

                is Effect.HidePostAfterReport -> setReportedPostId(effect.postId)
            }
        }
    }

    HomePostScreen(
        intent = homePostViewModel::intent,
        uiState = uiState,
    )

    when (uiState.isOpenModal) {
        HomePostBottomModalState.OPEN_COMMENT -> HomePostCommentBottomSheetDialog(
            comment = uiState.inWritingComment,
            closeSheet = { homePostViewModel.intent(Intent.OnClosedModal) },
            onCommentValueChanged = { homePostViewModel.intent(Intent.OnCommentValueChanged(it)) },
        )

        HomePostBottomModalState.OPEN_MENU -> HomePostMenuBottomSheetDialog(
            closeSheet = { homePostViewModel.intent(Intent.OnClosedModal) },
            onClickEdit = { homePostViewModel.intent(Intent.OnClickEditPost) },
            onClickDelete = { homePostViewModel.intent(Intent.OnClickDeletePost) },
            onClickReport = { homePostViewModel.intent(Intent.OnClickReportPost) },
        )

        HomePostBottomModalState.OPEN_REPORT -> ReportRoute(
            reportRouteState = reportRouteState,
            closeSheet = { homePostViewModel.intent(Intent.OnClosedModal) },
            reportPost = { homePostViewModel.intent(Intent.OnReportPost(uiState.postId)) },
        )

        HomePostBottomModalState.CLOSED -> Unit
    }
}
