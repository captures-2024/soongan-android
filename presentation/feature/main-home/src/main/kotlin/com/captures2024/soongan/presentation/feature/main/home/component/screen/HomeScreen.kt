package com.captures2024.soongan.presentation.feature.main.home.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeComponent
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeContestInfoBottomSheet
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeFailedComponent
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeInitComponent
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeViewModel

@Composable
internal fun HomeScreen(
    state: HomeViewModel.State,
    onClickContestInfo: () -> Unit,
    onClickPostList: () -> Unit,
    onClickRegister: () -> Unit,
    onClickPost: (PostInfoDto) -> Unit,
    onClickRetry: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val commonModifier = Modifier
        .fillMaxSize()
        .background(color = SGColor.BG.background)

    when (state.initState) {
        HomeViewModel.State.InitState.INIT -> HomeInitComponent(
            modifier = commonModifier,
        )

        HomeViewModel.State.InitState.FAIL -> HomeFailedComponent(
            modifier = commonModifier,
            onClickRetry = onClickRetry,
        )

        else -> HomeComponent(
            state = state,
            modifier = commonModifier,
            onClickContestInfo = onClickContestInfo,
            onClickPostList = onClickPostList,
            onClickRegister = onClickRegister,
            onClickPost = onClickPost,
        )
    }

    if (state.isShowContestInfoBottomSheet) {
        HomeContestInfoBottomSheet(
            onDismissRequest = onDismissRequest,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen_Init() {
    SGTheme {
        HomeScreen(
            state = HomeViewModel.State(
                initState = HomeViewModel.State.InitState.INIT,
                contestInfo = HomeContestInfoDto(
                    contestType = "",
                    subject = "",
                    startAt = "",
                    endAt = "",
                ),
                postInfoList = emptyList(),
                maxRegisterPostCount = 3,
                isShowContestInfoBottomSheet = false,
            ),
            onClickContestInfo = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
            onClickRetry = {},
            onDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen_Success() {
    SGTheme {
        HomeScreen(
            state = HomeViewModel.State(
                initState = HomeViewModel.State.InitState.SUCCESS,
                contestInfo = HomeContestInfoDto(
                    contestType = "weekly",
                    subject = "평화",
                    startAt = "2025.05.16",
                    endAt = "2025.05.31",
                ),
                postInfoList = emptyList(),
                maxRegisterPostCount = 3,
                isShowContestInfoBottomSheet = false,
            ),
            onClickContestInfo = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
            onClickRetry = {},
            onDismissRequest = {},
        )
    }
}
