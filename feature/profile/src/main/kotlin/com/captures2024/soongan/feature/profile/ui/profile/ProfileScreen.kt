package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel

@Composable
internal fun ProfileScreen(
    uiState: ProfileViewModel.State,
    intent: (ProfileViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 26.dp),
    ) {
        ProfileScreenHeader(
            userProfile = uiState.userProfile,
            modifier = Modifier.padding(start = 20.dp, end = 16.dp),
            onClickNotification = { intent(ProfileViewModel.Intent.OnClickNotification) },
            onClickMenu = { intent(ProfileViewModel.Intent.OnClickMenu) },
        )
        HeightSpacer(28.dp)
        ProfileScreenBody(
            myPosts = uiState.myPosts,
            paginationStatus = uiState.paginationStatus,
            isRefreshing = uiState.isRefreshing,
            onRefresh = { intent(ProfileViewModel.Intent.RefreshMyGallery) },
            onLoadNextPage = { intent(ProfileViewModel.Intent.LoadNextPage) },
            onClickPhoto = { intent(ProfileViewModel.Intent.OnClickPhoto(it)) },
            onClickRegistrationText = { intent(ProfileViewModel.Intent.OnClickRegistrationText) },
        )
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(uiState = ProfileViewModel.State(), intent = {})
}
