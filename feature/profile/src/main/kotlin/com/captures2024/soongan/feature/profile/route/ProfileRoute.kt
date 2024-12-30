package com.captures2024.soongan.feature.profile.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.util.sgBottomBarPadding
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.feature.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect
import com.captures2024.soongan.feature.profile.ui.profile.ProfileMenuBottomSheet
import com.captures2024.soongan.feature.profile.ui.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileRoute(
    navigateToEditProfile: (userProfile: UserProfile) -> Unit,
    navigateToNotification: () -> Unit,
    navigateToHomePost: (userPhoto: UserPost.PhotoPost) -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by profileViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSideEffect.NavigateToEditProfile -> navigateToEditProfile(sideEffect.userProfile)

                ProfileSideEffect.NavigateToNotification -> navigateToNotification()

                is ProfileSideEffect.NavigateToHomePost -> navigateToHomePost(sideEffect.userPhoto)
            }
        }
    }

    ProfileScreen(
        uiState = uiState,
        modifier = Modifier.sgBottomBarPadding(),
        onClickNotification = { profileViewModel.intent(ProfileIntent.OnClickNotification) },
        onClickMenu = { profileViewModel.intent(ProfileIntent.OnClickMenu) },
        onClickUserPhoto = { profileViewModel.intent(ProfileIntent.OnClickPhoto(it)) }
    )

    if (uiState.isOpenBottomSheet) {
        ProfileMenuBottomSheet(
            closeSheet = { profileViewModel.intent(ProfileIntent.OnCloseBottomSheet) },
            onClickMenuItem = { profileViewModel.intent(it.intent) }
        )
    }
}
