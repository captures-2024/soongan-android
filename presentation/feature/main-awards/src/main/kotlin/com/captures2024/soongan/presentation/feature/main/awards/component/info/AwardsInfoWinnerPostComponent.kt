package com.captures2024.soongan.presentation.feature.main.awards.component.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun AwardsInfoWinnerPostComponent(
    winnerPost: AwardsPostDto,
    modifier: Modifier = Modifier,
    onClickPost: (postId: Long) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        AwardsInfoTopPostComponent(
            topPost = winnerPost,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth(),
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
            modifier = Modifier.padding(
                start = 13.dp,
                top = 9.dp,
                bottom = 11.dp,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun AwardsInfoWinnerPostComponent_Preview() {
    AwardsInfoWinnerPostComponent(
        winnerPost = AwardsPostDto(
            postId = 0,
            title = "title_0",
            imageUrl = "",
            nickname = "nickname_0",
            score = "0",
            status = AwardsPostStatusType.ACTIVE,
        ),
        onClickPost = {},
    )
}
