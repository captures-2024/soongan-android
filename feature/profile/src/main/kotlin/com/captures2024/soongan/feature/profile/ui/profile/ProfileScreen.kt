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
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.feature.profile.state.profile.ProfileUIState

@Composable
internal fun ProfileScreen(
    uiState: ProfileUIState,
    modifier: Modifier = Modifier,
    onClickNotification: () -> Unit = {},
    onClickMenu: () -> Unit = {},
    onClickUserPhoto: (UserPost.PhotoPost) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 52.dp)
    ) {
        ProfileScreenHeader(
            userProfile = uiState.userProfile,
            onClickNotification = onClickNotification,
            onClickMenu = onClickMenu
        )
        HeightSpacer(28.dp)
        ProfileScreenBody(
            userPhotos = uiState.userPosts,
            onClickUserPhoto = onClickUserPhoto
        )
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(uiState = ProfileUIState())
}