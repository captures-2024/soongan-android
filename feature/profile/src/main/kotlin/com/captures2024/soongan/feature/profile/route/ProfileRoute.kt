package com.captures2024.soongan.feature.profile.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.feature.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.navigation.ProfileMenuItem
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
        onClickNotification = { profileViewModel.intent(ProfileIntent.OnClickNotification) },
        onClickMenu = { profileViewModel.intent(ProfileIntent.OnClickMenu) },
        onClickUserPhoto = { profileViewModel.intent(ProfileIntent.OnClickPhoto(it)) }
    )

    fun navigateToProfileDestination(
        profileMenuItem: ProfileMenuItem,
    ) {
        when (profileMenuItem) {
            ProfileMenuItem.EDIT -> profileViewModel.intent(ProfileIntent.OnClickEdit)
            ProfileMenuItem.NOTIFICATION_SETTING -> profileViewModel.intent(ProfileIntent.OnClickNotificationSetting)
            ProfileMenuItem.TERMS_AND_POLICY -> profileViewModel.intent(ProfileIntent.OnClickTermsAndPolicy)
            ProfileMenuItem.FAQ -> profileViewModel.intent(ProfileIntent.OnClickFAQ)
            ProfileMenuItem.WITHDRAW -> profileViewModel.intent(ProfileIntent.OnClickWithdraw)
            ProfileMenuItem.SIGN_OUT -> profileViewModel.intent(ProfileIntent.OnClickSignOut)
        }
    }

    if (uiState.isOpenBottomSheet) {
        ProfileMenuBottomSheet(
            closeSheet = { profileViewModel.intent(ProfileIntent.OnCloseBottomSheet) },
            onClickMenuItem = { navigateToProfileDestination(it) }
        )
    }
}
