package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.viewmodel.main.award.WinnerPost

@Composable
internal fun AwardsInfoWinnerPostComponent(
    winnerPost: WinnerPost,
    modifier: Modifier = Modifier,
    onClickPost: (postId: Long) -> Unit,
) {
    // default value: height(240) // 16:9 ratio: width(360)
    val sizeModifier =
        when (winnerPost.isDefaultOrientation) {
            true -> Modifier
                .height(240.dp)
                .fillMaxWidth()

            false -> Modifier.width(360.dp)
        }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        AwardsInfoTopPostComponent(
            topPost = winnerPost.topPost,
            modifier = sizeModifier,
            isWinnerPost = true,
            onClickPost = onClickPost,
        )
        SGText(
            text = winnerPost.title,
            style = getSGNonScaleTextStyle(
                color = SGColor.black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-0.5).em,
            ),
            modifier = Modifier.padding(start = 13.dp, top = 9.dp, bottom = 11.dp),
        )
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoWinnerPostComponent_Preview() {
    AwardsInfoWinnerPostComponent(
        winnerPost = WinnerPost(),
        onClickPost = {},
    )
}
