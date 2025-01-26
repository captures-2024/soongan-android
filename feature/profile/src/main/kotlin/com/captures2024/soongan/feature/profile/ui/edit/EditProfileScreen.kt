package com.captures2024.soongan.feature.profile.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.EditingState

@Composable
internal fun EditProfileScreen(
    uiState: EditingState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onClickProfileImage: () -> Unit = {},
    onNicknameChanged: (String) -> Unit = {},
    onIntroductionChanged: (String) -> Unit = {},
    onClickEditButton: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
    ) {
        EditProfileScreenHeader(
            onBackPressed = onBackPressed
        )
        HeightSpacer(8.dp)
        EditProfileScreenBody(
            uiState = uiState,
            onClickProfileImage = onClickProfileImage,
            onNicknameChanged = onNicknameChanged,
            onIntroductionChanged = onIntroductionChanged,
            onClickEdit = onClickEditButton
        )
    }
}

@DevicePreviews
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(
        uiState = EditingState()
    )
}