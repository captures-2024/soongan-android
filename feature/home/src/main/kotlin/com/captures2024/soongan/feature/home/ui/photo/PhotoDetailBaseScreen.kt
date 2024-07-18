package com.captures2024.soongan.feature.home.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconBack2
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.ui.gallery.HomeGalleryButton

@Composable
internal fun PhotoDetailBaseScreen(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
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
                HomeGalleryButton(onClick = onClickBackButton) {
                    Icon(
                        imageVector = MyIconPack.IconBack2,
                        contentDescription = "back",
                        tint = PrimaryA
                    )
                }
            }
        },
        bottomBar = bottomBar,
        containerColor = Color.Transparent,
        content = content
    )
}

@DevicePreviews
@Composable
private fun PhotoDetailBaseScreenPreview() {
    PhotoDetailBaseScreen {

    }
}