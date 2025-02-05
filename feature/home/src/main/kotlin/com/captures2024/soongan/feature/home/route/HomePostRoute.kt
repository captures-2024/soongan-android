package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState
import com.captures2024.soongan.feature.home.ui.post.HomePostMenuBottomSheetDialog
import com.captures2024.soongan.feature.home.ui.post.HomePostScreen
import com.captures2024.soongan.feature.home.ui.post.comment.HomePostCommentBottomSheetDialog

@Composable
internal fun HomePostRoute(
    navigateToBack: () -> Unit,
    navigateToHomePostPhoto: (String) -> Unit,
    homePostViewModel: HomePostViewModel = hiltViewModel(),
) {
    val uiState by homePostViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        homePostViewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomePostViewModel.Effect.NavigateToBack -> navigateToBack()

                is HomePostViewModel.Effect.NavigateToHomePostPhoto -> navigateToHomePostPhoto(effect.url)
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
            closeSheet = { homePostViewModel.intent(HomePostViewModel.Intent.OnClosedModal) },
            onCommentValueChanged = { homePostViewModel.intent(HomePostViewModel.Intent.OnCommentValueChanged(it)) }
        )

        HomePostBottomModalState.OPEN_MENU -> HomePostMenuBottomSheetDialog(
            closeSheet = { homePostViewModel.intent(HomePostViewModel.Intent.OnClosedModal) },
            onClickEdit = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickEditPost) },
            onClickDelete = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickDeletePost) },
            onClickReport = { homePostViewModel.intent(HomePostViewModel.Intent.OnClickReportPost) },
        )

        HomePostBottomModalState.OPEN_REPORT -> ReportRoute(
            targetId = uiState.postId.toLong(),
            targetType = ReportTargetType.WEEKLY_POST,
            closeSheet = { homePostViewModel.intent(HomePostViewModel.Intent.OnClosedModal) },
        )

        HomePostBottomModalState.CLOSED -> Unit
    }
}