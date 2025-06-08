package com.captures2024.soongan.presentation.feature.main.awards.component.awards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
internal fun AwardsNoContestComponent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AwardsTopBarComponent(
            modifier = Modifier.align(Alignment.TopCenter),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SGText(
                text = stringResource(R.string.awards_no_contest_text_1),
                style = getSGNonScaleTextStyle(
                    color = SGColor.black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )
            HeightSpacer(20.dp)
            SGText(
                text = stringResource(R.string.awards_no_contest_text_2),
                style = getSGNonScaleTextStyle(
                    color = SGColor.black,
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
private fun AwardsNoContestComponent_Preview() {
    AwardsNoContestComponent()
}
