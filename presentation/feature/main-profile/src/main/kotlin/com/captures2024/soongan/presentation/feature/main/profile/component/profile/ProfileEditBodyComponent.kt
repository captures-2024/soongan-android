package com.captures2024.soongan.presentation.feature.main.profile.component.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileEditViewModel
import com.captures2024.soongan.presentation.viewmodel.model.UserProfile
import com.captures2024.soongan.core.designsystem.ui.R as RDesign

@Composable
internal fun ProfileEditBodyComponent(
    state: ProfileEditViewModel.State,
    modifier: Modifier = Modifier,
    onClickProfileImage: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    onIntroductionValueChanged: (String) -> Unit,
    onClickEdit: () -> Unit,
) {
    val editingState = state.editingState
    val isValidNickname = editingState.isValidNickname == Validation.NicknameValidState.Success
    val isValidIntroduction = editingState.isValidIntroduction == Validation.IntroductionValidState.Success

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileBox(
            profileImage = editingState.editingProfile.profileImageUrl,
            onClick = onClickProfileImage,
        )

        HeightSpacer(44.dp)

        ProfileEditOutlinedTextField(
            value = editingState.editingProfile.nickname,
            onValueChange = onNicknameValueChanged,
            detailTitle = stringResource(R.string.profile_edit_nickname_description),
            placeholder = stringResource(R.string.profile_edit_nickname_placeholder),
            isInvalid = editingState.isDuplicatedNickname || !isValidNickname,
            hint = when {
                editingState.isDuplicatedNickname -> stringResource(R.string.profile_edit_nickname_error_duplicated)
                editingState.isValidNickname == Validation.NicknameValidState.Regex -> stringResource(R.string.profile_edit_nickname_error_regex)
                editingState.isValidNickname == Validation.NicknameValidState.Length -> stringResource(R.string.profile_edit_nickname_error_length)
                else -> AppConst.EMPTY_STRING
            },
            maxInputLength = state.maxNicknameLength,
        )

        HeightSpacer(36.dp)

        ProfileEditOutlinedTextField(
            value = editingState.editingProfile.selfIntroduction,
            onValueChange = onIntroductionValueChanged,
            detailTitle = stringResource(R.string.profile_edit_introduction_description),
            placeholder = stringResource(R.string.profile_edit_introduction_placeholder),
            isInvalid = !isValidIntroduction,
            hint = when {
                editingState.isValidIntroduction == Validation.IntroductionValidState.Length -> stringResource(
                    id = R.string.profile_edit_introduction_error_length,
                )
                else -> AppConst.EMPTY_STRING
            },
            maxInputLength = state.maxIntroductionLength,
        )

        WeightSpacer(1f)

        SGTextButtonType2(
            text = stringResource(R.string.button_edit_confirm),
            modifier = Modifier.widthIn(min = 120.dp),
            enabled = state.isEditable && !editingState.isDuplicatedNickname && isValidNickname && isValidIntroduction,
            onClick = onClickEdit,
        )

        WeightSpacer(1f)
    }
}

@Composable
private fun ProfileBox(
    profileImage: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(180.dp)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null,
            ),
    ) {
        AsyncImage(
            model = profileImage,
            contentDescription = stringResource(R.string.image_description),
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(RDesign.drawable.ic_border_profile),
            error = painterResource(RDesign.drawable.ic_border_profile),
        )

        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            ProfileAddIconComponent()
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileEditBodyComponent() {
    SGTheme {
        ProfileEditBodyComponent(
            state = ProfileEditViewModel.State(
                userProfile = UserProfile(),
                editingState = ProfileEditViewModel.State.EditingProfileState(),
                isShowEditBottomSheet = false,
            ),
            onClickProfileImage = {},
            onNicknameValueChanged = {},
            onIntroductionValueChanged = {},
            onClickEdit = {},
        )
    }
}
