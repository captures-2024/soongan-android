package com.captures2024.soongan.feature.profile.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.util.sgBottomBarPadding
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel.Effect
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel.Intent
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.ProfileBottomSheet
import com.captures2024.soongan.feature.profile.ui.profile.ProfileScreen
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel.Effect as BtmShtEffect
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel.Intent as BtmShtIntent

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
                is Effect.Profile.NavigateToNotification -> navigateToNotification()

                is Effect.Profile.NavigateToHomePost -> navigateToHomePost(sideEffect.postId)

                is Effect.Profile.NavigateToRegistrationPost -> navigateToRegistrationPost()

                is Effect.Profile.NavigateToEditProfile -> navigateToEditProfile()

                is Effect.Edit -> Unit
            }
        }
    }

    LaunchedEffect(Unit) {
        profileBtmShtVM.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is BtmShtEffect.CloseBottomSheet -> profileVM.intent(Intent.Profile.OnCloseBottomSheet)

                is BtmShtEffect.SendRequestNavigateToEditProfile ->
                    profileVM.intent(Intent.Profile.OnMenuItemClicked(menuItem = ProfileBtmShtMenuItem.EDIT))

                is BtmShtEffect.NavigateToHome -> TODO()
            }
        }
    }

    ProfileScreen(
        uiState = uiState,
        modifier = Modifier.sgBottomBarPadding(),
        onClickNotification = { profileVM.intent(Intent.Profile.OnClickNotification) },
        onClickMenu = { profileVM.intent(Intent.Profile.OnClickMenu) },
        onRefresh = { profileVM.intent(Intent.Profile.RefreshMyGallery) },
        onLoadNextPage = { profileVM.intent(Intent.Profile.LoadNextPage) },
        onClickMyPost = { profileVM.intent(Intent.Profile.OnClickPhoto(it)) },
        onClickRegistrationText = { profileVM.intent(Intent.Profile.OnClickRegistrationText) },
    )

    if (uiState.isOpenBottomSheet) {
        ProfileBottomSheet(
            uiState = btmShtUiState,
            closeSheet = { profileBtmShtVM.intent(BtmShtIntent.OnCloseBottomSheet) },
            onClickMenuItem = { profileBtmShtVM.intent(BtmShtIntent.OnClickMenuItem(it)) },
            onBackIdle = { profileBtmShtVM.intent(BtmShtIntent.OnBackIdle) },
            onCheckProcess = { profileBtmShtVM.intent(BtmShtIntent.OnCheckProcess(it)) },
            onDoneProcess = { profileBtmShtVM.intent(BtmShtIntent.OnDoneProcess) },
            onPushSettingChanged = { profileBtmShtVM.intent(BtmShtIntent.OnPushSettingChanged(it)) },
        )
    }
}
