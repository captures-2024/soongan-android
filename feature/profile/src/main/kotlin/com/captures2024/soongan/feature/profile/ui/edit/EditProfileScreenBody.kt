package com.captures2024.soongan.feature.profile.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.profile.ui.edit.component.EditProfileButton
import com.captures2024.soongan.feature.profile.ui.edit.component.MiniAddIcon
import com.captures2024.soongan.feature.profile.ui.edit.component.ProfileOutlinedTextField

@Composable
internal fun EditProfileScreenBody(
    modifier: Modifier = Modifier,
    onClickProfileImage: () -> Unit = {},
    onNicknameChanged: (String) -> Unit = {},
    onIntroductionChanged: (String) -> Unit = {},
    onClickEdit: () -> Unit = {},
) {
    var nickname by remember { mutableStateOf("") }
    var selfIntroduction by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileBox(onClick = onClickProfileImage)
        HeightSpacer(44.dp)
        ProfileOutlinedTextField(
            value = nickname,
            onValueChange = onNicknameChanged,
            hint = "닉네임은 한글, 영문, 숫자만 입력해주세요",
            placeHolder = "닉네임을 입력해주세요",
        )
        HeightSpacer(36.dp)
        ProfileOutlinedTextField(
            value = selfIntroduction,
            onValueChange = onIntroductionChanged,
            hint = "자기소개를 입력해주세요",
            placeHolder = "본인을 소개해주세요",
        )
        HeightSpacer(130.dp)
        EditProfileButton("수정하기", onClick = onClickEdit)
    }
}

@Composable
private fun ProfileBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(180.dp)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = null,
            contentDescription = null,
            modifier = Modifier.size(180.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_border_profile),
            error = painterResource(R.drawable.ic_border_profile)
        )
        Box(modifier = Modifier.align(Alignment.BottomEnd)) {
            MiniAddIcon()
        }
    }
}

@DevicePreviews
@Composable
private fun EditProfileScreenBodyPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        EditProfileScreenBody()
    }
}