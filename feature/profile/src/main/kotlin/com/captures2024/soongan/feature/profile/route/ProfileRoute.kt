package com.captures2024.soongan.feature.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.util.extension.sgBottomBarPadding
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.ui.profile.ProfileScreen
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.ProfileBottomSheet

@Composable
internal fun ProfileRoute(
    navigateToNotification: () -> Unit,
    navigateToHomePost: (Long) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToFAQ: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by profileViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                ProfileViewModel.Effect.NavigateToNotification -> navigateToNotification()

                is ProfileViewModel.Effect.NavigateToHomePost -> navigateToHomePost(sideEffect.postId)

                ProfileViewModel.Effect.NavigateToRegistrationPost -> navigateToRegistrationPost()

                ProfileViewModel.Effect.NavigateToEditProfile -> navigateToEditProfile()

                ProfileViewModel.Effect.NavigateToFAQ -> navigateToFAQ()
            }
        }
    }

//    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
//        profileViewModel.intent(ProfileViewModel.Intent.Init)
//    }

    ProfileScreen(
        uiState = uiState,
        intent = profileViewModel::intent,
        modifier = Modifier.sgBottomBarPadding()
    )

    if (uiState.isOpenBottomSheet) {
        ProfileBottomSheet(
            closeSheet = { profileViewModel.intent(ProfileViewModel.Intent.OnCloseBottomSheet(it)) },
        )
    }
}
