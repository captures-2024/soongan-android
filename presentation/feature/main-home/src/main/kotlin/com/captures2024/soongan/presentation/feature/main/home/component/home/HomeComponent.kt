package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeViewModel

@Composable
internal fun HomeComponent(
    state: HomeViewModel.State,
    modifier: Modifier = Modifier,
    onClickContestInfo: () -> Unit,
    onClickPostList: () -> Unit,
    onClickRegister: () -> Unit,
    onClickPost: (PostInfoDto) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 40.dp,
                horizontal = 16.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        HomeTopBarComponent(
            subject = state.contestInfo.subject,
        )

        HomeBodyComponent(
            state = state,
            modifier = Modifier.weight(1f),
            onClickRegister = onClickRegister,
            onClickPost = onClickPost,
        )

        HomeBottomBarComponent(
            onClickContestInfo = onClickContestInfo,
            onClickPostList = onClickPostList,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeComponent() {
    SGTheme {
        HomeComponent(
            state = HomeViewModel.State(
                initState = HomeViewModel.State.InitState.INIT,
                maxRegisterPostCount = 3,
            ),
            modifier = Modifier.fillMaxSize(),
            onClickContestInfo = {},
            onClickPostList = {},
            onClickRegister = {},
            onClickPost = {},
        )
    }
}
