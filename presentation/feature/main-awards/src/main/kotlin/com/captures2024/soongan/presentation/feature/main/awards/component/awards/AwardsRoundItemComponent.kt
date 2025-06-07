package com.captures2024.soongan.presentation.feature.main.awards.component.awards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
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
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsContestInfo
import com.captures2024.soongan.presentation.designsystem.ui.R as RDesignSystem

@Composable
internal fun AwardsRoundItemComponent(
    awardsContestInfo: AwardsContestInfo,
    modifier: Modifier = Modifier,
    onCLickContestSubject: (round: Int) -> Unit,
) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(awardsContestInfo.imageUrl)
        .build()

    Box(
        modifier = modifier
            .width(361.dp)
            .height(126.dp)
            .clickable { onCLickContestSubject(awardsContestInfo.round) },
    ) {
        AsyncImage(
            model = model,
            contentDescription = "contest winner image",
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(RDesignSystem.drawable.test),
            error = painterResource(RDesignSystem.drawable.test),
            contentScale = ContentScale.FillWidth,
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(color = SGColor.Grayscale.black100.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center,
        ) {
            SGText(
                text = awardsContestInfo.subject,
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

@DevicePreviews
@Composable
private fun AwardsRoundItemComponent_Preview() {
    AwardsRoundItemComponent(
        awardsContestInfo = AwardsContestInfo(),
        onCLickContestSubject = {},
    )
}
