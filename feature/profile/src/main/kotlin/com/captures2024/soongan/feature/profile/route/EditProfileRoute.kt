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
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel.Intent
import com.captures2024.soongan.core.viewmodel.profile.ProfileViewModel.Effect
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
                profileViewModel.intent(Intent.Edit.OnProfileImageChanged(it.toString()))
            }
        }

    LaunchedEffect(Unit) {
        profileViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                Effect.Edit.NavigateToBack -> navigateToBack()

                Effect.Edit.OpenMediaPicker -> {
                    pickMedia.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }

                is Effect.Profile -> Unit
            }
        }
    }

    EditProfileScreen(
        uiState = uiState.editingState,
        onBackPressed = { profileViewModel.intent(Intent.Edit.OnBackPressed) },
        onClickProfileImage = { profileViewModel.intent(Intent.Edit.OnClickProfileImage) },
        onNicknameChanged = { profileViewModel.intent(Intent.Edit.OnNicknameChanged(it)) },
        onIntroductionChanged = { profileViewModel.intent(Intent.Edit.OnIntroductionChanged(it)) },
        onClickEditButton = { profileViewModel.intent(Intent.Edit.OnClickEditButton) }
    )

    if (uiState.isOpenProfileImageBottomSheet) {
        EditProfileBottomSheet(
            closeSheet = { profileViewModel.intent(Intent.Edit.OnCloseEditBottomSheet) },
            onClickDefaultProfileImage = { profileViewModel.intent(Intent.Edit.OnClickDefaultProfileImage) },
            openPhotoPicker = { profileViewModel.intent(Intent.Edit.OpenPhotoPicker) }
        )
    }
}