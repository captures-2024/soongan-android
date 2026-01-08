package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileEditScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileEditViewModel

@Composable
internal fun ProfileEditRoute(
    navigateToBack: () -> Unit,
    viewModel: ProfileEditViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            viewModel.intent(ProfileEditViewModel.Intent.OnProfileImageChanged(it.toString()))
        }
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileEditViewModel.Effect.NavigateToBack -> navigateToBack()
                is ProfileEditViewModel.Effect.OpenMediaPicker -> pickMedia.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly,
                    ),
                )
            }
        }
    }

    ProfileEditScreen(
        state = state,
        onClickBack = { viewModel.intent(ProfileEditViewModel.Intent.OnClickBack) },
        onClickProfileImage = { viewModel.intent(ProfileEditViewModel.Intent.OnClickProfileImage) },
        onNicknameValueChanged = { viewModel.intent(ProfileEditViewModel.Intent.OnNicknameValueChanged(it)) },
        onIntroductionValueChanged = { viewModel.intent(ProfileEditViewModel.Intent.OnIntroductionValueChanged(it)) },
        onClickEdit = { viewModel.intent(ProfileEditViewModel.Intent.OnClickEditButton) },
        onDismissRequest = { viewModel.intent(ProfileEditViewModel.Intent.OnDismissRequestEditBottomSheet) },
        onClickOpenPhotoPicker = { viewModel.intent(ProfileEditViewModel.Intent.OpenPhotoPicker) },
        onClickDefaultImage = { viewModel.intent(ProfileEditViewModel.Intent.OnChangeDefaultProfileImage) },
    )
}
