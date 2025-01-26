package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel

@Composable
internal fun ProfileScreen(
    uiState: ProfileViewModel.State,
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit = {},
    onClickMenu: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onLoadNextPage: () -> Unit = {},
    onClickMyPost: (Int) -> Unit = {},
    onClickRegistrationText: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 26.dp)
    ) {
        ProfileScreenHeader(
            userProfile = uiState.userProfile,
            modifier = Modifier.padding(start = 20.dp, end = 16.dp),
            onClickNotification = onClickNotification,
            onClickMenu = onClickMenu
        )
        HeightSpacer(28.dp)
        ProfileScreenBody(
            myPosts = uiState.myPosts,
            paginationStatus = uiState.paginationStatus,
            isRefreshing = uiState.isRefreshing,
            onRefresh = onRefresh,
            onLoadNextPage = onLoadNextPage,
            onClickMyPost = onClickMyPost,
            onClickRegistrationText = onClickRegistrationText,
        )
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(uiState = ProfileViewModel.State())
}