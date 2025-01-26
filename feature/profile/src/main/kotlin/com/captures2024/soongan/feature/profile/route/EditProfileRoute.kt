package com.captures2024.soongan.feature.profile.route

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.profile.ProfileViewModel
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.EditI
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.EditSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.ProfileSE
import com.captures2024.soongan.feature.profile.ui.edit.EditProfileBottomSheet
import com.captures2024.soongan.feature.profile.ui.edit.EditProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditProfileRoute(
    navigateToBack: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by profileViewModel.state.collectAsStateWithLifecycle()

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                profileViewModel.intent(EditI.OnProfileImageChanged(it.toString()))
            }
        }

    LaunchedEffect(Unit) {
        profileViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                EditSE.NavigateToBack -> navigateToBack()

                EditSE.OpenMediaPicker -> {
                    pickMedia.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }

                is ProfileSE -> Unit
            }
        }
    }

    EditProfileScreen(
        uiState = uiState.editingState,
        onBackPressed = { profileViewModel.intent(EditI.OnBackPressed) },
        onClickProfileImage = { profileViewModel.intent(EditI.OnClickProfileImage) },
        onNicknameChanged = { profileViewModel.intent(EditI.OnNicknameChanged(it)) },
        onIntroductionChanged = { profileViewModel.intent(EditI.OnIntroductionChanged(it)) },
        onClickEditButton = { profileViewModel.intent(EditI.OnClickEditButton) }
    )

    if (uiState.isOpenProfileImageBottomSheet) {
        EditProfileBottomSheet(
            closeSheet = { profileViewModel.intent(EditI.OnCloseEditBottomSheet) },
            onClickDefaultProfileImage = { profileViewModel.intent(EditI.OnClickDefaultProfileImage) },
            openPhotoPicker = { profileViewModel.intent(EditI.OpenPhotoPicker) }
        )
    }
}