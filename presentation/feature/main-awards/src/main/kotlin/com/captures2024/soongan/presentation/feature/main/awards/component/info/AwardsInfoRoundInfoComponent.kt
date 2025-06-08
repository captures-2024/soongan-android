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
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.awards.R
import com.captures2024.soongan.presentation.viewmodel.main.award.ContestInfo

@Composable
internal fun AwardsInfoRoundInfoComponent(
    contestInfo: ContestInfo,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SGText(
            text = stringResource(
                R.string.awards_info_round_info_title_text,
                contestInfo.subject,
                contestInfo.round,
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
            text = contestInfo.startAt + stringResource(R.string.awards_info_round_info_period_delimiter) + contestInfo.endAt,
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
                contestInfo.allPostCount,
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
        contestInfo = ContestInfo(),
    )
}
