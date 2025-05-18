package com.captures2024.soongan.presentation.feature.main.home.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillComment
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.feature.main.home.R

@Composable
internal fun HomeBodyNotEmptyComponent(
    maxCount: Int,
    postInfoList: List<PostInfoDto>,
    modifier: Modifier = Modifier,
    onClickRegister: () -> Unit,
    onClickPost: (PostInfoDto) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RegisterButtonComponent(
            maxCount = maxCount,
            postCount = postInfoList.size,
            onClick = onClickRegister,
        )

        postInfoList.forEach { postInfo ->
            PostComponent(
                postInfo = postInfo,
                onClick = onClickPost,
            )
        }
    }
}

@Composable
private fun RegisterButtonComponent(
    maxCount: Int,
    postCount: Int,
    onClick: () -> Unit,
) {
    val isValidPost = postCount < maxCount
    val blur = 4.dp

    Box(
        modifier = Modifier
            .padding(blur)
            .width(60.dp + blur)
            .height(257.dp + blur)
            .dropShadow(
                shape = RectangleShape,
                blur = blur,
            )
            .let {
                when (isValidPost) {
                    true -> it.clickable(onClick = onClick)
                    false -> it
                }
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = when (isValidPost) {
                        true -> SGColor.Grayscale.white
                        false -> SGColor.Grayscale.black40
                    }
                ),
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillPlus,
                contentDescription = stringResource(R.string.register_button_description),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(28.dp),
                tint = when (isValidPost) {
                    true -> SGColor.Grayscale.black100
                    false -> SGColor.Grayscale.white
                },
            )

            SGText(
                text = "${postCount}/${maxCount}",
                style = getSGNonScaleTextStyle(
                    color = when (isValidPost) {
                        true -> SGColor.Grayscale.black100
                        false -> SGColor.Grayscale.white
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 14.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
            )
        }
    }
}

@Composable
private fun PostComponent(
    postInfo: PostInfoDto,
    modifier: Modifier = Modifier,
    onClick: (PostInfoDto) -> Unit,
) {
    val commonShape = RectangleShape
    val maxBlur = 8.dp

    Column(modifier = modifier) {
        AsyncImage(
            model = postInfo.imageUrl,
            contentDescription = stringResource(R.string.photo_button_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .widthIn(min = 131.dp + maxBlur)
                .height(257.dp + maxBlur)
                .dropShadow(
                    shape = commonShape,
                    offsetX = 6.dp,
                    offsetY = 6.dp,
                    blur = 3.dp,
                )
                .dropShadow(
                    shape = commonShape,
                    offsetX = (-4).dp,
                    offsetY = 4.dp,
                )
                .background(
                    color = SGColor.Grayscale.white,
                    shape = commonShape,
                )
                .clickable { onClick(postInfo) },
        )

        HeightSpacer(8.dp)

        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            InteractionIconBox(
                imageVector = MyIconPack.IconFillHeart,
                contentDescription = stringResource(R.string.heart_button_description),
                interactionCount = postInfo.likeCount,
            )

            WidthSpacer(8.dp)

            InteractionIconBox(
                imageVector = MyIconPack.IconNonFillComment,
                contentDescription = stringResource(R.string.comment_button_description),
                interactionCount = postInfo.commentCount,
            )
        }
    }
}

@Composable
private fun InteractionIconBox(
    imageVector: ImageVector,
    contentDescription: String,
    interactionCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(12.dp),
        )

        WidthSpacer(4.dp)

        SGText(
            text = interactionCount.toString(),
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 12.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-2).em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeBodyNotEmptyComponent_Default() {
    SGTheme {
        HomeBodyNotEmptyComponent(
            maxCount = 3,
            postInfoList = listOf(
                PostInfoDto(),
            ),
            onClickRegister = {},
            onClickPost = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewHomeBodyNotEmptyComponent_Max() {
    SGTheme {
        HomeBodyNotEmptyComponent(
            maxCount = 3,
            postInfoList = listOf(
                PostInfoDto(),
                PostInfoDto(),
                PostInfoDto(),
            ),
            onClickRegister = {},
            onClickPost = {},
        )
    }
}
