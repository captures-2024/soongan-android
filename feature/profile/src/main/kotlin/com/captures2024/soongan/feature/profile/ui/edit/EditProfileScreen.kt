package com.captures2024.soongan.feature.profile.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.EditingProfileState
import com.captures2024.soongan.core.viewmodel.profile.ProfileEditViewModel

@Composable
internal fun EditProfileScreen(
    editingState: EditingProfileState,
    intent: (ProfileEditViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
    ) {
        EditProfileScreenHeader(
            onBackPressed = { intent(ProfileEditViewModel.Intent.OnBackPressed) },
        )
        HeightSpacer(8.dp)
        EditProfileScreenBody(
            editingState = editingState,
            onClickProfileImage = { intent(ProfileEditViewModel.Intent.OnClickProfileImage) },
            onNicknameChanged = { intent(ProfileEditViewModel.Intent.OnNicknameChanged(it)) },
            onIntroductionChanged = { intent(ProfileEditViewModel.Intent.OnIntroductionChanged(it)) },
            onClickEdit = { intent(ProfileEditViewModel.Intent.OnClickEditButton) },
        )
    }
}

@DevicePreviews
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(
        editingState = EditingProfileState(),
        intent = {},
    )
}
