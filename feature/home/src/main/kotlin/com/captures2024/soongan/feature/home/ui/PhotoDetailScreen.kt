package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconBack2
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconComment
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconMenu
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.mock.GalleryImage
import com.captures2024.soongan.feature.home.samplePhotos

@Composable
internal fun PhotoDetailScreen(
    modifier: Modifier = Modifier,
    item: GalleryImage
) {
    val showShimmer = remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFD9D9D9))
            .paint(
                painter = painterResource(id = R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
            ),
        topBar = @Composable {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                HomeGalleryButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = MyIconPack.IconBack2,
                        contentDescription = "back",
                        tint = PrimaryA
                    )
                }
            }
        },
        bottomBar = @Composable {
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
                        tint = PrimaryA
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
        },
        containerColor = Color.Transparent
    ) { paddingValue ->
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
                        .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
                        .widthIn(max = 360.dp)
                        .heightIn(max = 460.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(0.dp),
                            color = Color(0x40000000),
                            blur = 3.dp,
                            offsetX = 6.dp,
                            offsetY = 6.dp,
                        ),
                    contentScale = ContentScale.FillWidth,
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
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


@DevicePreviews
@Composable
private fun PhotoDetailScreenPreview() {
    PhotoDetailScreen(item = samplePhotos[0])
}