package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    myPhotos: List<UserPost.PhotoPost> = emptyList(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PrimaryB)
            .padding(top = 52.dp),
    ) {
        ProfileScreenHeader(profileImageUrl = "")
        HeightSpacer(28.dp)
        ProfileScreenBody(myPhotos = myPhotos)
    }
}

@DevicePreviews
@Composable
private fun ProfileScreenPreview() {
    val myPhotos = samplePhotos.map { it as UserPost.PhotoPost }

    ProfileScreen(myPhotos = myPhotos)
}