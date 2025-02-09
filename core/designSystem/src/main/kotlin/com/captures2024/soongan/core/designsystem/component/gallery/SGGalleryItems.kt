package com.captures2024.soongan.core.designsystem.component.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.captures2024.soongan.core.designsystem.component.shimmerBrush
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
fun SGGallerySkeletonItem(
    modifier: Modifier = Modifier,
    height: Int = 200,
) = Box(
    modifier = modifier
        .width(190.dp)
        .height(height.dp)
        .dropShadow(
            shape = RectangleShape,
            color = SGColor.black.copy(alpha = 0.25f),
            offsetY = 4.dp,
            blur = 4.dp
        )
        .background(SGColor.buttonDisableGray)
        .background(brush = shimmerBrush(targetValue = 1300f))
)

@Composable
fun SGGalleryImageItem(
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val showShimmer = remember { mutableStateOf(true) }

    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = modifier
            .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
            .width(190.dp)
            .heightIn(
                min = 100.dp,
                max = 300.dp,
            )
            .dropShadow(
                shape = RoundedCornerShape(0.dp),
                color = SGColor.black.copy(alpha = 0.2f),
                offsetY = 4.dp,
                blur = 4.dp,
            )
            .clickable {
                if (!showShimmer.value) {
                    onClick()
                }
            },
        onSuccess = { showShimmer.value = false },
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
fun SGGalleryEmptyItem(
    emptyText: String,
    registrationText: String,
    modifier: Modifier = Modifier,
    onClickRegistrationText: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = emptyText,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 12.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
        )

        Box(
            modifier = Modifier
                .size(
                    width = 92.dp,
                    height = 40.dp,
                )
                .clickable { onClickRegistrationText() },
            contentAlignment = Alignment.Center,
        ) {
            SGText(
                text = registrationText,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 12.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                    textDecoration = TextDecoration.Underline,
                ),
            )
        }
    }
}

@Composable
fun SGGalleryPaginatingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = SGColor.black
        )
    }
}

@Composable
fun SGGalleryErrorItem(
    errorText: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        SGText(
            text = errorText,
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 12.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun SGGallerySkeletonItemPreview() {
    Box(
        modifier = Modifier.background(SGColor.white)
            .padding(16.dp)
    ) {
        SGGallerySkeletonItem()
    }
}

@DevicePreviews
@Composable
private fun SGGalleryImageItemPreview() {
    Box(
        modifier = Modifier.background(SGColor.white)
            .padding(16.dp)
    ) {
        SGGalleryImageItem(imageUrl = "")
    }
}

@DevicePreviews
@Composable
private fun SGGalleryEmptyItemPreview() {
    Box(
        modifier = Modifier.background(SGColor.white)
            .padding(16.dp)
    ) {
        SGGalleryEmptyItem(
            emptyText = "아직 참가한 내역이 없어요.",
            registrationText = "참가하러 가기"
        )
    }
}

@DevicePreviews
@Composable
private fun SGGalleryPaginatingItemPreview() {
    Box(
        modifier = Modifier.background(SGColor.white)
            .padding(16.dp)
    ) {
        SGGalleryPaginatingItem()
    }
}

@DevicePreviews
@Composable
private fun SGGalleryErrorItemPreview() {
    Box(
        modifier = Modifier.background(SGColor.white)
            .padding(16.dp)
    ) {
        SGGalleryErrorItem(errorText = "게시글을 불러올 수 없어요.")
    }
}