package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavOptions
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconComment
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconMenu
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.mock.samplePhotos
import com.captures2024.soongan.feature.home.state.PhotoDetailModalState
import com.captures2024.soongan.feature.home.state.PhotoDetailModelState
import com.captures2024.soongan.feature.home.state.PhotoDetailUIState

@Composable
internal fun PhotoDetailScreen(
    modifier: Modifier = Modifier,
    uiState: PhotoDetailUIState,
    onClickBack: () -> Unit = {},
    onClickImage: (String, NavOptions?) -> Unit = { _, _ ->},
    onClickMenu: () -> Unit = {},
    onClickComment: () -> Unit = {},
) {
    val item = uiState.modelState.model
    val showShimmer = remember { mutableStateOf(true) }

    PhotoDetailBaseScreen(
        onClickBackButton = onClickBack,
        bottomBar = @Composable {
            if (item.id != -1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(83.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(0.dp),
                            color = Color(0x40000000),
                            blur = 4.dp,
                            offsetX = 0.dp,
                            offsetY = (-2).dp
                        )
                        .background(color = Color.White)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = MyIconPack.IconMenu,
                        contentDescription = "menu",
                        tint = PrimaryA
                    )
                    Row {
                        Icon(
                            imageVector = MyIconPack.IconFillHeart,
                            contentDescription = "heart",
                            tint = PrimaryA
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        NonScaleText(
                            text = "1.2m",
                            color = PrimaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.width(31.dp))
                        Icon(
                            imageVector = MyIconPack.IconComment,
                            contentDescription = "comment",
                            tint = PrimaryA,
                            modifier = Modifier.clickable {
                                onClickComment()
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        NonScaleText(
                            text = "1.2m",
                            color = PrimaryA,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
        },
    ) { paddingValue ->
        if (item.id != -1) {
            Column(
                modifier = Modifier
                    .padding(paddingValue)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(360.dp)
                        .height(460.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.url)
                            .build(),
                        contentDescription = item.title,
                        modifier = modifier
                            .background(
                                shimmerBrush(
                                    targetValue = 1300f,
                                    showShimmer = showShimmer.value
                                )
                            )
                            .widthIn(max = 360.dp)
                            .heightIn(max = 460.dp)
                            .dropShadow(
                                shape = RoundedCornerShape(0.dp),
                                color = Color(0x40000000),
                                blur = 3.dp,
                                offsetX = 6.dp,
                                offsetY = 6.dp,
                            )
                            .clickable {
                                val navOptions = NavOptions.Builder()
                                    .build()
                                onClickImage(item.url, navOptions)
                            },
                        contentScale = ContentScale.FillWidth,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 40.dp)
                ) {
                    NonScaleText(
                        text = "무제",
                        color = PrimaryA,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NonScaleText(
                        text = "@dkddkq222",
                        color = PrimaryA,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


@DevicePreviews
@Composable
private fun PhotoDetailScreenInitPreview() {
    PhotoDetailScreen(
        uiState = PhotoDetailUIState.Init
    )
}

@DevicePreviews
@Composable
private fun PhotoDetailScreenLoadImagePreview() {
    PhotoDetailScreen(
        uiState = PhotoDetailUIState.LoadImage(
            menuModalState = PhotoDetailModalState.Close,
            commentModalState = PhotoDetailModalState.Close,
            modelState = PhotoDetailModelState.Init(samplePhotos[0] as UserPost.PhotoPost)
        )
    )
}