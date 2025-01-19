package com.captures2024.soongan.feature.profile.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.util.sgBottomBarPadding
import com.captures2024.soongan.feature.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.BottomSheetI
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.ProfileI
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.BottomSheetSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.EditSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.ProfileSE
import com.captures2024.soongan.feature.profile.ui.profile.ProfileMenuBottomSheet
import com.captures2024.soongan.feature.profile.ui.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileRoute(
    navigateToNotification: () -> Unit,
    navigateToHomePost: (Int) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToEditProfile: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by profileViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSE.NavigateToNotification -> navigateToNotification()

                is ProfileSE.NavigateToHomePost -> navigateToHomePost(sideEffect.postId)

                is ProfileSE.NavigateToRegistrationPost -> navigateToRegistrationPost()

                is BottomSheetSE.NavigateToEditProfile -> navigateToEditProfile()

                is EditSE -> Unit
            }
        }
    }

    ProfileScreen(
        uiState = uiState,
        modifier = Modifier.sgBottomBarPadding(),
        onClickNotification = { profileViewModel.intent(ProfileI.OnClickNotification) },
        onClickMenu = { profileViewModel.intent(ProfileI.OnClickMenu) },
        onRefresh = { profileViewModel.intent(ProfileI.RefreshMyGallery) },
        onLoadNextPage = { profileViewModel.intent(ProfileI.LoadNextPage) },
        onClickMyPost = { profileViewModel.intent(ProfileI.OnClickPhoto(it)) },
        onClickRegistrationText = { profileViewModel.intent(ProfileI.OnClickRegistrationText) },
    )

    if (uiState.isOpenBottomSheet) {
        ProfileMenuBottomSheet(
            closeSheet = { profileViewModel.intent(BottomSheetI.OnCloseBottomSheet) },
            onClickMenuItem = { profileViewModel.intent(it.intent) }
        )
    }
}
