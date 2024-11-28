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

@Composable
internal fun EditProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 14.dp)
            .padding(horizontal = 20.dp)
    ) {
        EditProfileScreenHeader(
            onBackPressed = { TODO("navigateUp") }
        )
        HeightSpacer(28.dp)
        EditProfileScreenBody(
            onClickProfileImage = { TODO("open photoPicker") },
            onNicknameChanged = { TODO() },
            onIntroductionChanged = { TODO() },
            onClickEdit = { TODO("navigateUp") }
        )
    }
}

@DevicePreviews
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen()
}