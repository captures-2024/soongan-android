package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.R

@Composable
internal fun AwardsInfoRoundInfoComponent(
    awardsInfo: AwardsDetailDto,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = stringResource(
                R.string.awards_info_round_info_title_text,
                awardsInfo.subject,
                awardsInfo.round.toString(),
            ),
            style = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
        )
        HeightSpacer(20.dp)
        SGText(
            text = awardsInfo.startAt + stringResource(R.string.awards_info_round_info_period_delimiter) + awardsInfo.endAt,
            style = getSGNonScaleTextStyle(
                color = SGColor.black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-0.5).em,
            ),
        )
        HeightSpacer(14.dp)
        SGText(
            text = stringResource(
                R.string.awards_info_round_info_all_post_count_text,
                awardsInfo.postsCount,
            ),
            style = getSGNonScaleTextStyle(
                color = SGColor.black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-0.5).em,
            ),
        )
        HeightSpacer(92.dp)
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoRoundInfoComponent_Preview() {
    AwardsInfoRoundInfoComponent(
        awardsInfo = AwardsDetailDto(
            subject = "subject",
            round = 1L,
            startAt = "startAt",
            endAt = "endAt",
            postsCount = 30L,
            prizePosts = List(7) { index ->
                when (index) {
                    0 -> AwardsPostDto(
                        postId = 0,
                        title = "title_0",
                        imageUrl = "",
                        nickname = "nickname_0",
                        score = "0",
                        status = AwardsPostStatusType.ACTIVE,
                    )

                    else -> {
                        AwardsPostDto(
                            postId = index.toLong(),
                            title = "title_$index",
                            imageUrl = "",
                            nickname = "nickname_$index",
                            score = "$index",
                            status = AwardsPostStatusType.ACTIVE,
                        )
                    }
                }
            },
        ),
    )
}
