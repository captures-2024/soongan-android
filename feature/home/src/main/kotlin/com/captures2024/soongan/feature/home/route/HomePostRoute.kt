package com.captures2024.soongan.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.component.dialog.SGDoubleButtonDialog
import com.captures2024.soongan.core.designsystem.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel.Effect
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel.Intent
import com.captures2024.soongan.core.viewmodel.model.HomePostBottomModalState
import com.captures2024.soongan.core.viewmodel.model.HomePostDialogModalState
import com.captures2024.soongan.feature.home.R
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
        targetType = ReportTargetType.WEEKLY_POST
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
            onCommentValueChanged = { homePostViewModel.intent(Intent.OnCommentValueChanged(it)) }
        )

        HomePostBottomModalState.OPEN_MENU -> HomePostMenuBottomSheetDialog(
            closeSheet = { homePostViewModel.intent(Intent.OnClosedModal) },
            onClickEdit = { homePostViewModel.intent(Intent.OnClickEditPost) },
            onClickDelete = { homePostViewModel.intent(Intent.OnClickDeletePost) },
            onClickReport = { homePostViewModel.intent(Intent.OnClickReportPost) },
            isMyPost = uiState.isMyPost
        )

        HomePostBottomModalState.OPEN_REPORT -> ReportRoute(
            reportRouteState = reportRouteState,
            closeSheet = { homePostViewModel.intent(Intent.OnClosedModal) },
            reportPost = { homePostViewModel.intent(Intent.OnHidePost) }
        )

        HomePostBottomModalState.CLOSED -> Unit
    }

    when (uiState.isOpenDialogModal) {
        HomePostDialogModalState.OPEN_DELETE -> SGDoubleButtonDialog(
            content = stringResource(R.string.open_dialog_modal_delete_content_msg),
            confirmContent = stringResource(R.string.open_dialog_modal_delete_content_confirm_msg),
            onClickConfirm = { homePostViewModel.intent(Intent.OnDeletePostRemote) },
            cancelContent = stringResource(R.string.open_dialog_modal_delete_content_cancel_msg),
            onClickCancel = { homePostViewModel.intent(Intent.OnClosedDialogModal) },
            onDismissRequest = { homePostViewModel.intent(Intent.OnClosedDialogModal) }
        )

        HomePostDialogModalState.OPEN_COMPLETE -> SGSingleButtonDialog(
            content = stringResource(R.string.open_dialog_modal_complete_content_msg),
            confirmContent = stringResource(R.string.open_dialog_modal_confirm_msg),
            onClickConfirm = { homePostViewModel.intent(Intent.OnHidePost) },
            onDismissRequest = { homePostViewModel.intent(Intent.OnClosedDialogModal) }
        )

        HomePostDialogModalState.OPEN_FAIL -> SGSingleButtonDialog(
            content = stringResource(R.string.open_dialog_modal_fail_content_msg),
            confirmContent = stringResource(R.string.open_dialog_modal_confirm_msg),
            onClickConfirm = { homePostViewModel.intent(Intent.OnClosedDialogModal) },
            onDismissRequest = { homePostViewModel.intent(Intent.OnClosedDialogModal) }
        )

        HomePostDialogModalState.CLOSED -> Unit
    }
}