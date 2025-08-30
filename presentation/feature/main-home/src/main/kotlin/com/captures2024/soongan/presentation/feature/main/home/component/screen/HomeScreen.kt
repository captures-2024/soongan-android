package com.captures2024.soongan.presentation.feature.main.home.component.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.designsystem.ui.util.extension.sgBottomBarPadding
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeContestInfoBottomSheet
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeEmptyComponent
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeFailedComponent
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeInitComponent
import com.captures2024.soongan.presentation.feature.main.home.component.home.HomeSuccessComponent
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeViewModel
import com.captures2024.soongan.presentation.viewmodel.model.enums.HomeInfoState

@Composable
internal fun HomeScreen(
    state: HomeViewModel.State,
    onClickRetry: () -> Unit,
    onClickPostList: () -> Unit,
    onClickRegister: () -> Unit,
    onClickPost: (PostInfoDto) -> Unit,
    onClickContestInfo: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    val commonModifier = Modifier
        .fillMaxSize()
        .sgBottomBarPadding()

    when (state.homeInfo.homeInfoState) {
        HomeInfoState.INIT -> HomeInitComponent(
            modifier = commonModifier,
        )

        HomeInfoState.ERROR -> HomeFailedComponent(
            isLoading = state.isLoading,
            modifier = commonModifier,
            onClickRetry = onClickRetry,
        )

        HomeInfoState.EMPTY -> HomeEmptyComponent(
            isLoading = state.isLoading,
            modifier = commonModifier,
            onClickPostList = onClickPostList,
        )

        HomeInfoState.SUCCESS -> HomeSuccessComponent(
            isLoading = state.isLoading,
            homeInfo = state.homeInfo,
            modifier = commonModifier,
            onClickRegister = onClickRegister,
            onClickPost = onClickPost,
            onClickPostList = onClickPostList,
            onClickContestInfo = onClickContestInfo,
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
                homeInfo = HomeViewModel.State.HomeInfo(
                    homeInfoState = HomeInfoState.INIT,
                ),
            ),
            onClickRetry = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
            onClickContestInfo = {},
            onDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen_ERROR() {
    SGTheme {
        HomeScreen(
            state = HomeViewModel.State(
                homeInfo = HomeViewModel.State.HomeInfo(
                    homeInfoState = HomeInfoState.ERROR,
                ),
            ),
            onClickRetry = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
            onClickContestInfo = {},
            onDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen_EMPTY() {
    SGTheme {
        HomeScreen(
            state = HomeViewModel.State(
                homeInfo = HomeViewModel.State.HomeInfo(
                    homeInfoState = HomeInfoState.EMPTY,
                ),
            ),
            onClickRetry = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
            onClickContestInfo = {},
            onDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeScreen_SUCCESS() {
    SGTheme {
        HomeScreen(
            state = HomeViewModel.State(
                homeInfo = HomeViewModel.State.HomeInfo(
                    homeInfoState = HomeInfoState.SUCCESS,
                ),
            ),
            onClickRetry = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
            onClickContestInfo = {},
            onDismissRequest = {},
        )
    }
}
