package com.captures2024.soongan.presentation.feature.main.post.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoBodyComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoBottomBarComponent
import com.captures2024.soongan.presentation.feature.main.post.component.info.PostInfoTopBarComponent
import com.captures2024.soongan.presentation.feature.main.post.component.menu.PostInfoMenuBottomSheet
import com.captures2024.soongan.presentation.feature.main.post.component.report.ReportBottomSheet
import com.captures2024.soongan.presentation.viewmodel.main.post.PostInfoViewModel

@Composable
internal fun PostInfoScreen(
    state: PostInfoViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickMenu: () -> Unit,
    onClickHeart: () -> Unit,
    onClickPhoto: () -> Unit,
    onDismissRequestMenuBottomSheet: () -> Unit,
    onClickDelete: () -> Unit,
    onClickEditPost: () -> Unit,
    onClickReport: () -> Unit,
    onDismissRequestReportBottomSheet: () -> Unit,
    onDoneReport: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.BG.background),
        topBar = @Composable {
            PostInfoTopBarComponent(
                round = state.round,
                subject = state.subject,
                onClickBack = onClickBack,
            )
        },
        bottomBar = @Composable {
            state.postInfo?.let { info ->
                PostInfoBottomBarComponent(
                    isLiked = info.isLiked,
                    likeCount = info.likeCount,
                    onClickMenu = onClickMenu,
                    onClickHeart = onClickHeart,
                )
            }
        },
        containerColor = SGColor.transparent,
    ) { innerPadding ->
        state.postInfo?.let { info ->
            PostInfoBodyComponent(
                postInfo = info,
                modifier = Modifier.padding(innerPadding),
                onClickPhoto = onClickPhoto,
            )
        }
    }

    if (state.isShowMenuBottomSheet) {
        PostInfoMenuBottomSheet(
            isMyPost = state.isMyPost,
            onDismissRequest = onDismissRequestMenuBottomSheet,
            onClickDelete = onClickDelete,
            onClickEditPost = onClickEditPost,
            onClickReport = onClickReport,
        )
    }

    if (state.isShowReportBottomSheet) {
        ReportBottomSheet(
            id = state.postId,
            onDismissRequest = onDismissRequestReportBottomSheet,
            onDoneReport = onDoneReport,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoScreen_Default() {
    SGTheme {
        PostInfoScreen(
            state = PostInfoViewModel.State(
                round = 0,
                subject = "평화",
                postId = -1L,
                postInfo = null,
                isShowMenuBottomSheet = false,
                isShowReportBottomSheet = false,
                currentMemberNickname = "",
            ),
            onClickBack = {},
            onClickMenu = {},
            onClickHeart = {},
            onClickPhoto = {},
            onDismissRequestMenuBottomSheet = {},
            onClickDelete = {},
            onClickEditPost = {},
            onClickReport = {},
            onDismissRequestReportBottomSheet = {},
            onDoneReport = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoScreen_Info() {
    SGTheme {
        PostInfoScreen(
            state = PostInfoViewModel.State(
                round = 0,
                subject = "평화",
                postId = -1L,
                postInfo = PostInfoDto(),
                isShowMenuBottomSheet = false,
                isShowReportBottomSheet = false,
                currentMemberNickname = "",
            ),
            onClickBack = {},
            onClickMenu = {},
            onClickHeart = {},
            onClickPhoto = {},
            onDismissRequestMenuBottomSheet = {},
            onClickDelete = {},
            onClickEditPost = {},
            onClickReport = {},
            onDismissRequestReportBottomSheet = {},
            onDoneReport = {},
        )
    }
}
