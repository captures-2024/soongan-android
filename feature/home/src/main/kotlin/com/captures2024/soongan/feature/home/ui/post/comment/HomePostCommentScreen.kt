package com.captures2024.soongan.feature.home.ui.post.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos

@Composable
internal fun HomePostCommentScreen(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data((samplePhotos[0] as UserPost.PhotoPost).url)
                .build(),
            contentDescription = "user_profile",
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.width(8.dp))
        HomePostCommentScreenBody()
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier.size(
                width = 20.dp,
                height = 20.dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = "menu",
                tint = PrimaryA,
                modifier = Modifier.size(
                    width = 2.dp,
                    height = 12.dp
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostCommentScreenPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        HomePostCommentScreen()
    }
}