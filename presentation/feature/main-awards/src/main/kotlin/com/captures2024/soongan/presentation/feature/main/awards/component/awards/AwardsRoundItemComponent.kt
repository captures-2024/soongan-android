package com.captures2024.soongan.presentation.feature.main.awards.component.awards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.presentation.designsystem.ui.component.shimmerBrush
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.designsystem.ui.R as RDesignSystem

@Composable
internal fun AwardsRoundItemComponent(
    awards: AwardsDefaultDto,
    modifier: Modifier = Modifier,
    onClickContestSubject: (AwardsDefaultDto) -> Unit,
) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(awards.thumbnailImageUrl)
        .build()

    Box(
        modifier = modifier
            .width(361.dp)
            .height(126.dp)
            .clickable { onClickContestSubject(awards) },
    ) {
        AwardsThumbnailComponent(model = model)
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(color = SGColor.Grayscale.black100.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center,
        ) {
            SGText(
                text = awards.subject,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.white,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
        }
    }
}

@Composable
private fun AwardsThumbnailComponent(
    model: Any?,
) {
    val showShimmer = remember { mutableStateOf(true) }

    AsyncImage(
        model = model,
        contentDescription = "contest winner image",
        modifier = Modifier.fillMaxSize()
            .background(
                brush = shimmerBrush(
                    targetValue = 1300f,
                    showShimmer = showShimmer.value,
                ),
            )
            .dropShadow(
                shape = RoundedCornerShape(0.dp),
                color = SGColor.black.copy(alpha = 0.2f),
                offsetY = 4.dp,
                blur = 4.dp,
            ),
        onSuccess = { showShimmer.value = false },
        onError = { showShimmer.value = false },
        error = painterResource(RDesignSystem.drawable.test),
        contentScale = ContentScale.Crop,
    )
}

@DevicePreviews
@Composable
private fun AwardsRoundItemComponent_Preview() {
    AwardsRoundItemComponent(
        awards = AwardsDefaultDto(
            id = 0L,
            round = 0,
            subject = "subject",
            startAt = "startAt",
            endAt = "endAt",
            announcedAt = "announcedAt",
            thumbnailImageUrl = "thumbnailImageUrl",
        ),
        onClickContestSubject = {},
    )
}
