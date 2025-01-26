package com.captures2024.soongan.feature.profile.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.util.sgBottomBarPadding
import com.captures2024.soongan.feature.profile.ProfileBtmShtViewModel
import com.captures2024.soongan.feature.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.feature.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.ProfileI
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
    profileVM: ProfileViewModel = hiltViewModel(),
    profileBtmShtVM: ProfileBtmShtViewModel = hiltViewModel(),
) {
    val uiState by profileVM.state.collectAsStateWithLifecycle()
    val btmShtUiState by profileBtmShtVM.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileVM.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSE.NavigateToNotification -> navigateToNotification()

                is ProfileSE.NavigateToHomePost -> navigateToHomePost(sideEffect.postId)

                is ProfileSE.NavigateToRegistrationPost -> navigateToRegistrationPost()

                is ProfileSE.NavigateToEditProfile -> navigateToEditProfile()

                is EditSE -> Unit
            }
        }
    }

    LaunchedEffect(Unit) {
        profileBtmShtVM.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ProfileBtmShtViewModel.Effect.CloseBottomSheet ->
                    profileVM.intent(ProfileI.OnCloseBottomSheet)

                is ProfileBtmShtViewModel.Effect.SendRequestNavigateToEditProfile ->
                    profileVM.intent(ProfileI.OnMenuItemClicked(menuItem = ProfileBtmShtMenuItem.EDIT))

                is ProfileBtmShtViewModel.Effect.NavigateToHome -> TODO()
            }
        }
    }

    ProfileScreen(
        uiState = uiState,
        modifier = Modifier.sgBottomBarPadding(),
        onClickNotification = { profileVM.intent(ProfileI.OnClickNotification) },
        onClickMenu = { profileVM.intent(ProfileI.OnClickMenu) },
        onRefresh = { profileVM.intent(ProfileI.RefreshMyGallery) },
        onLoadNextPage = { profileVM.intent(ProfileI.LoadNextPage) },
        onClickMyPost = { profileVM.intent(ProfileI.OnClickPhoto(it)) },
        onClickRegistrationText = { profileVM.intent(ProfileI.OnClickRegistrationText) },
    )

    if (uiState.isOpenBottomSheet) {
        ProfileMenuBottomSheet(
            uiState = btmShtUiState,
            closeSheet =
            { profileBtmShtVM.intent(ProfileBtmShtViewModel.Intent.OnCloseBottomSheet) },
            onClickMenuItem =
            { profileBtmShtVM.intent(ProfileBtmShtViewModel.Intent.OnClickMenuItem(it)) },
            onBackIdle =
            { profileBtmShtVM.intent(ProfileBtmShtViewModel.Intent.OnBackIdle) },
            onCheckProcess =
            { profileBtmShtVM.intent(ProfileBtmShtViewModel.Intent.OnCheckProcess(it)) },
            onDoneProcess =
            { profileBtmShtVM.intent(ProfileBtmShtViewModel.Intent.OnDoneProcess) },
            onPushSettingChanged =
            { profileBtmShtVM.intent(ProfileBtmShtViewModel.Intent.OnPushSettingChanged(it)) },
        )
    }
}
