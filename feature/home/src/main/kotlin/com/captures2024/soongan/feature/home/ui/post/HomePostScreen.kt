package com.captures2024.soongan.feature.home.ui.post

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.ui.R
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconCircleButton
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.viewmodel.home.HomePostViewModel

@Composable
internal fun HomePostScreen(
    intent: (HomePostViewModel.Intent) -> Unit,
    uiState: HomePostViewModel.State,
    modifier: Modifier = Modifier,
) {
    val showShimmer = remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = SGColor.tempPrimaryD)
            .paint(
                painter = painterResource(id = R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
            ),
        topBar = @Composable {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                SGIconCircleButton(
                    imageVector = MyIconPack.IconNonFillLeftArrow,
                    contentDescription = "back",
                    iconWidth = 20.dp,
                    iconHeight = 16.dp,
                    color = SGColor.primaryA,
                    onClick = { intent(HomePostViewModel.Intent.OnClickBack) },
                )
            }
        },
        bottomBar = @Composable {
            HomePostScreenBottomBar(
                likeCount = uiState.post.likeCount,
//                commentCount = uiState.post.commentCount,
                onClickMenu = { intent(HomePostViewModel.Intent.OnClickMenu) },
                onClickHeart = { intent(HomePostViewModel.Intent.OnClickHeart) },
//                onClickComment = { intent(HomePostViewModel.Intent.OnClickComment) },
            )
        },
        containerColor = SGColor.transparent,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
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
                        .data(uiState.post.imageUrl)
                        .build(),
                    contentDescription = uiState.post.title,
                    modifier = modifier
                        .width(360.dp)
                        .height(460.dp)
                        .background(
                            brush = shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = showShimmer.value,
                            ),
                        )
                        .dropShadow(
                            shape = RoundedCornerShape(0.dp),
                            color = SGColor.black.copy(alpha = 0.3f),
                            blur = 3.dp,
                            offsetX = 6.dp,
                            offsetY = 6.dp,
                        )
                        .clickable(
                            onClick = { intent(HomePostViewModel.Intent.OnClickPhoto) },
                        ),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 40.dp),
            ) {
                SGText(
                    text = uiState.post.title,
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.nanumSquareNeo,
                        letterSpacing = 0.em,
                    ),
                )

                HeightSpacer(8.dp)

                SGText(
                    text = "@${uiState.post.nickname}",
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp,
                        fontFamily = SGTypography.poppins,
                        letterSpacing = (-2).em,
                    ),
                )
            }
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostScreenPreview() {
    HomePostScreen(
        intent = {},
        uiState = HomePostViewModel.State(
            postId = 1,
            post = PostInfoDto(
                postId = 1,
                imageUrl = "",
                title = "무제",
                nickname = "테스트닉네임",
            ),
        ),
    )
}
