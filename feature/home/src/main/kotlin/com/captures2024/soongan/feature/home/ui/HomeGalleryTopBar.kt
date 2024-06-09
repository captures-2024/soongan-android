package com.captures2024.soongan.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.design.R
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.theme.boxShadow
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.theme.innerShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomeGalleryTopBar(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    var scrolledY = 0f
    var previousOffset = 0

    Row(
        modifier = modifier
            .graphicsLayer {
                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                translationY = scrolledY * 0.5f
                previousOffset = lazyListState.firstVisibleItemScrollOffset
            }
            .fillMaxWidth()
            .height(100.dp)
//            .background(color = Color.Black)
            .background(color = PrimaryB)
            .paint(
                painter = painterResource(id = R.drawable.background_home_gallery),
                contentScale = ContentScale.Crop,
                alpha = 0.8f
            )
            .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(44.dp)
                .innerShadow(
                    shape = CircleShape,
                    color = Color(0x4DFFFFFF),
                    blur = 2.dp,
                    offsetX = 1.dp,
                    offsetY = 1.dp,
                    spread = 0.dp
                )
                .innerShadow(
                    shape = CircleShape,
                    color = Color(0x80D4D4D4),
                    blur = 2.dp,
                    offsetX = (-1).dp,
                    offsetY = (-1).dp,
                    spread = 0.dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0x33D4D4D4),
                    blur = 10.dp,
                    offsetX = (-5).dp,
                    offsetY = 5.dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0x33D4D4D4),
                    blur = 10.dp,
                    offsetX = 5.dp,
                    offsetY = (-5).dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0xE6FFFFFF),
                    blur = 10.dp,
                    offsetX = (-5).dp,
                    offsetY = (-5).dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0xE6D4D4D4),
                    blur = 13.dp,
                    offsetX = 5.dp,
                    offsetY = 5.dp
                ),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBEBEB))
        ) {

        }

        NonScaleText(
            text = "W _ 1회차 | 평화",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(44.dp)
                .innerShadow(
                    shape = CircleShape,
                    color = Color(0x4DFFFFFF),
                    blur = 2.dp,
                    offsetX = 1.dp,
                    offsetY = 1.dp,
                    spread = 0.dp
                )
                .innerShadow(
                    shape = CircleShape,
                    color = Color(0x80D4D4D4),
                    blur = 2.dp,
                    offsetX = (-1).dp,
                    offsetY = (-1).dp,
                    spread = 0.dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0x33D4D4D4),
                    blur = 10.dp,
                    offsetX = (-5).dp,
                    offsetY = 5.dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0x33D4D4D4),
                    blur = 10.dp,
                    offsetX = 5.dp,
                    offsetY = (-5).dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0xE6FFFFFF),
                    blur = 10.dp,
                    offsetX = (-5).dp,
                    offsetY = (-5).dp
                )
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0xE6D4D4D4),
                    blur = 13.dp,
                    offsetX = 5.dp,
                    offsetY = 5.dp
                ),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEBEBEB))
        ) {

        }
    }
}

@DevicePreviews
@Composable
private fun HomeGalleryTopBarPreview() {
    HomeGalleryTopBar()
}