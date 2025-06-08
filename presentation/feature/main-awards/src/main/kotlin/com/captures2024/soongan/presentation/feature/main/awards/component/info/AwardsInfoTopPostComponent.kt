package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.R
import com.captures2024.soongan.presentation.viewmodel.main.award.TopPost
import com.captures2024.soongan.presentation.designsystem.ui.R as RDesignSystem

@Composable
internal fun AwardsInfoTopPostComponent(
    topPost: TopPost,
    modifier: Modifier = Modifier,
    isWinnerPost: Boolean = false,
    onClickPost: (postId: Long) -> Unit,
) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(topPost.imageUrl)
        .build()
    val badgePadding = if (isWinnerPost) 8.dp else 4.dp

    Box(
        modifier = modifier.clickable { onClickPost(topPost.postId) },
    ) {
        AsyncImage(
            model = model,
            contentDescription = "top post image",
            modifier = Modifier.fillMaxSize(),
            error = painterResource(RDesignSystem.drawable.test),
            contentScale = ContentScale.Crop,
        )
        TopPostBadge(
            value = stringResource(R.string.awards_info_top_post_nickname_badge_prefix_delimiter) + topPost.nickname,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(badgePadding),
        )
        TopPostBadge(
            value = topPost.voteLike.toString(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(badgePadding),
        )
    }
}

@Composable
private fun TopPostBadge(
    value: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(20.dp)
            .background(
                color = SGColor.black100.copy(alpha = 0.5f),
                shape = RoundedCornerShape(20.dp),
            )
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        SGText(
            text = value,
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.white,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-0.5).em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoTopPostComponent_Preview() {
    AwardsInfoTopPostComponent(
        topPost = TopPost(
            postId = 2L,
            imageUrl = "",
            nickname = "닉네임입니다열자리입",
            voteLike = 7777,
        ),
        onClickPost = {},
    )
}
