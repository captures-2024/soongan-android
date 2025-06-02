package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.component.profile.ProfileEditBodyComponent
import com.captures2024.soongan.presentation.feature.main.profile.component.profile.ProfileEditImageBottomSheet
import com.captures2024.soongan.presentation.feature.main.profile.component.profile.ProfileEditTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileEditViewModel
import com.captures2024.soongan.presentation.viewmodel.model.UserProfile

@Composable
internal fun ProfileEditScreen(
    state: ProfileEditViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onClickProfileImage: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    onIntroductionValueChanged: (String) -> Unit,
    onClickEdit: () -> Unit,
    onDismissRequest: () -> Unit,
    onClickOpenPhotoPicker: () -> Unit,
    onClickDefaultImage: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.Grayscale.white)
            .padding(horizontal = 20.dp),
    ) {
        ProfileEditTopBarComponent(
            onClickBack = onClickBack,
        )

        HeightSpacer(8.dp)

        ProfileEditBodyComponent(
            state = state,
            modifier = Modifier.verticalScroll(scrollState),
            onClickProfileImage = onClickProfileImage,
            onNicknameValueChanged = onNicknameValueChanged,
            onIntroductionValueChanged = onIntroductionValueChanged,
            onClickEdit = onClickEdit,
        )
    }

    if (state.isShowEditBottomSheet) {
        ProfileEditImageBottomSheet(
            onDismissRequest = onDismissRequest,
            onClickOpenPhotoPicker = onClickOpenPhotoPicker,
            onClickDefaultImage = onClickDefaultImage,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileEditScreen() {
    SGTheme {
        ProfileEditScreen(
            state = ProfileEditViewModel.State(
                userProfile = UserProfile(),
                editingState = ProfileEditViewModel.State.EditingProfileState(
                    editingProfile = UserProfile(),
                    isDuplicatedNickname = false,
                ),
                isShowEditBottomSheet = false,
            ),
            onClickBack = {},
            onClickProfileImage = {},
            onNicknameValueChanged = {},
            onIntroductionValueChanged = {},
            onClickEdit = {},
            onDismissRequest = {},
            onClickOpenPhotoPicker = {},
            onClickDefaultImage = {},
        )
    }
}
