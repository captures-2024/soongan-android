package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconHearts
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconMenu
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos

@Composable
internal fun CommentScreen(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 12.dp)
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data((samplePhotos[0] as UserPost.PhotoPost).url)
                .build(),
            contentDescription = "user_profile",
            modifier = Modifier
                .size(36.dp, 36.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.width(8.dp))
        CommentBodyScreen()
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = MyIconPack.IconMenu,
            contentDescription = "menu",
            tint = PrimaryA,
            modifier = Modifier.size(20.dp, 20.dp)
        )
    }
}

@Composable
private fun CommentBodyScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(fraction = 0.9f)
    ) {
        NonScaleText(
            text = "user1",
            color = PrimaryA,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        NonScaleText(
            text = "댓글 내용이 들어가면 될 거 같아요 여기까지면 되지 않을까요?",
            color = PrimaryA,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = MyIconPack.IconHearts,
                    contentDescription = "heart",
                    tint = PrimaryA.copy(alpha = 0.9f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                NonScaleText(
                    text = "0",
                    color = PrimaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(32.dp))
                NonScaleText(
                    text = "답글 달기",
                    color = PrimaryA.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            NonScaleText(
                text = "15시간 전",
                color = PrimaryA.copy(alpha = 0.6f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@DevicePreviews
@Composable
private fun CommentScreenPreview() {
    CommentScreen()
}