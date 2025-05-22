package com.captures2024.soongan.presentation.feature.main.post.component.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoBodyComponent(
    postInfo: PostInfoDto,
    modifier: Modifier = Modifier,
    onClickPhoto: () -> Unit,
) {
    val showShimmer = remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .width(360.dp)
                .height(460.dp),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(postInfo.imageUrl)
                    .build(),
                contentDescription = postInfo.title,
                modifier = modifier
                    .widthIn(max = 360.dp)
                    .heightIn(max = 460.dp)
                    .background(
                        brush = shimmerBrush(
                            targetValue = 1300f,
                            showShimmer = showShimmer.value,
                        ),
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(0.dp),
                        color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                        blur = 3.dp,
                        offsetX = 6.dp,
                        offsetY = 6.dp,
                    )
                    .clickable(onClick = onClickPhoto),
                contentScale = ContentScale.Fit,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 40.dp),
        ) {
            SGText(
                text = postInfo.title,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
            )

            HeightSpacer(8.dp)

            SGText(
                text = stringResource(R.string.post_info_nickname_prefix) + postInfo.nickname,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-2).em,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoBodyComponent() {
    SGTheme {
        PostInfoBodyComponent(
            postInfo = PostInfoDto(),
            onClickPhoto = {},
        )
    }
}
