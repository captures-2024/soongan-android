package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.PoppinsFontFamily
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos

@Composable
internal fun CommentScreen(
    modifier: Modifier = Modifier,
    profileSize: Int,
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
                .size(profileSize.dp, profileSize.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.width(8.dp))
        CommentBodyScreen()
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
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            fontFamily = PoppinsFontFamily
        )
        Spacer(modifier = Modifier.height(4.dp))
        NonScaleText(
            text = "댓글 내용이 들어가면 될 거 같아요 여기까지면 되지 않을까요?",
            color = PrimaryA,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp,
            fontFamily = NanumSquareNeoFontFamily
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = MyIconPack.IconNonFillHeart,
                    contentDescription = "heart",
                    tint = PrimaryA.copy(alpha = 0.9f),
                    modifier = Modifier.size(
                        width = 16.dp,
                        height = 16.dp
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                NonScaleText(
                    text = "0",
                    color = PrimaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = PoppinsFontFamily
                )
                Spacer(modifier = Modifier.width(32.dp))
                NonScaleText(
                    text = "답글 달기",
                    color = PrimaryA.copy(alpha = 0.9f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = NanumSquareNeoFontFamily
                )
            }
            NonScaleText(
                text = "15시간 전",
                color = PrimaryA.copy(alpha = 0.6f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = NanumSquareNeoFontFamily
            )
        }
    }
}

@DevicePreviews
@Composable
private fun CommentScreenPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        CommentScreen(
            profileSize = 36,
        )
    }
}