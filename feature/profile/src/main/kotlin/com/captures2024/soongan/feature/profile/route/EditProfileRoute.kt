package com.captures2024.soongan.feature.profile.route

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.profile.EditProfileViewModel
import com.captures2024.soongan.feature.profile.state.edit.EditProfileIntent
import com.captures2024.soongan.feature.profile.state.edit.EditProfileSideEffect
import com.captures2024.soongan.feature.profile.ui.edit.EditProfileScreen

@Composable
internal fun EditProfileRoute(
    navigateToBack: () -> Unit,
    editProfileViewModel: EditProfileViewModel = hiltViewModel(),
) {
    val uiState by editProfileViewModel.state.collectAsStateWithLifecycle()

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                editProfileViewModel.intent(EditProfileIntent.OnProfileImageChanged(it.toString()))
            }
        }

    LaunchedEffect(Unit) {
        editProfileViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                EditProfileSideEffect.NavigateToBack -> navigateToBack()

                EditProfileSideEffect.OpenMediaPicker -> {
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
        uiState = uiState,
        onBackPressed = { editProfileViewModel.intent(EditProfileIntent.OnBackPressed) },
        onClickProfileImage = { editProfileViewModel.intent(EditProfileIntent.OnClickProfileImage) },
        onNicknameChanged = { editProfileViewModel.intent(EditProfileIntent.OnNicknameChanged(it)) },
        onIntroductionChanged = { editProfileViewModel.intent(EditProfileIntent.OnIntroductionChanged(it)) },
        onClickEditButton = { editProfileViewModel.intent(EditProfileIntent.OnClickEditButton) }
    )
}