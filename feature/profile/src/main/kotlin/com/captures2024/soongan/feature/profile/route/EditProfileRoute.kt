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
import com.captures2024.soongan.core.viewmodel.profile.ProfileEditViewModel
import com.captures2024.soongan.feature.profile.ui.edit.EditProfileBottomSheet
import com.captures2024.soongan.feature.profile.ui.edit.EditProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditProfileRoute(
    navigateToBack: () -> Unit,
    profileEditViewModel: ProfileEditViewModel = hiltViewModel(),
) {
    val uiState by profileEditViewModel.state.collectAsStateWithLifecycle()

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                profileEditViewModel.intent(ProfileEditViewModel.Intent.OnProfileImageChanged(it.toString()))
            }
        }

    LaunchedEffect(Unit) {
        profileEditViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                ProfileEditViewModel.Effect.NavigateToBack -> navigateToBack()

                ProfileEditViewModel.Effect.OpenMediaPicker -> {
                    pickMedia.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            }
        }
    }

    EditProfileScreen(
        editingState = uiState.editingState,
        intent = profileEditViewModel::intent,
    )

    if (uiState.isOpenBottomSheet) {
        EditProfileBottomSheet(
            intent = profileEditViewModel::intent,
        )
    }
}