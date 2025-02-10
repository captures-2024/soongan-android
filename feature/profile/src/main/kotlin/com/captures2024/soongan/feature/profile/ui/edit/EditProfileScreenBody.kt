package com.captures2024.soongan.feature.profile.ui.edit

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
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WeightSpacer
import com.captures2024.soongan.core.designsystem.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.EditingProfileState
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.edit.component.MiniAddIcon
import com.captures2024.soongan.feature.profile.ui.edit.component.ProfileOutlinedTextField
import com.captures2024.soongan.core.design.R as RDesign
import com.captures2024.soongan.feature.profile.R as RProfile

@Composable
internal fun EditProfileScreenBody(
    editingState: EditingProfileState,
    modifier: Modifier = Modifier,
    onClickProfileImage: () -> Unit = {},
    onNicknameChanged: (String) -> Unit = {},
    onIntroductionChanged: (String) -> Unit = {},
    onClickEdit: () -> Unit = {},
) {
    val isValidNickname = editingState.isValidNickname == Validation.NicknameValidState.Success
    val isValidIntroduction =
        editingState.isValidIntroduction == Validation.IntroductionValidState.Success

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileBox(
            profileImage = editingState.editingProfile.profileImageUrl,
            onClick = onClickProfileImage
        )
        HeightSpacer(44.dp)
        ProfileOutlinedTextField(
            value = editingState.editingProfile.nickname,
            onValueChange = onNicknameChanged,
            detailTitle = stringResource(RProfile.string.input_nickname_detail_title),
            placeholder = stringResource(RProfile.string.input_nickname_placeholder),
            isInvalid = editingState.isDuplicatedNickname || !isValidNickname,
            hint = when {
                editingState.isDuplicatedNickname ->
                    stringResource(RProfile.string.input_nickname_fail_duplication_hint_text)

                editingState.isValidNickname == Validation.NicknameValidState.Regex ->
                    stringResource(RProfile.string.input_nickname_fail_regex_hint_text)

                editingState.isValidNickname == Validation.NicknameValidState.Length ->
                    stringResource(RProfile.string.input_nickname_fail_length_hint_text)

                else -> ""
            },
            maxInputLength = 10,
        )

        HeightSpacer(36.dp)

        ProfileOutlinedTextField(
            value = editingState.editingProfile.selfIntroduction,
            onValueChange = onIntroductionChanged,
            detailTitle = stringResource(RProfile.string.input_introduction_detail_title),
            placeholder = stringResource(RProfile.string.input_introduction_placeholder),
            isInvalid = !isValidIntroduction,
            hint = when {
                editingState.isValidIntroduction == Validation.IntroductionValidState.Length ->
                    stringResource(RProfile.string.input_introduction_fail_length_hint_text)

                else -> ""
            },
            maxInputLength = 20,
        )

        WeightSpacer(1f)

        SGTextButtonType2(
            text = stringResource(R.string.edit_complete_button_text),
            modifier = Modifier.widthIn(min = 120.dp),
            enabled = editingState.isEditable && !editingState.isDuplicatedNickname && isValidNickname && isValidIntroduction,
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
                indication = null
            )
    ) {
        AsyncImage(
            model = profileImage,
            contentDescription = "user profile Image",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(RDesign.drawable.ic_border_profile),
            error = painterResource(RDesign.drawable.ic_border_profile)
        )
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            MiniAddIcon()
        }
    }
}

@DevicePreviews
@Composable
private fun EditProfileScreenBodyPreview() {
    EditProfileScreenBody(
        editingState = EditingProfileState()
    )
}