package com.captures2024.soongan.feature.home.ui.post.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillHeart
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun HomePostCommentScreenBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(fraction = 0.9f)
    ) {
        SGText(
            text = "user1",
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                fontFamily = SGTypography.poppins,
                letterSpacing = 0.em,
            )
        )

        HeightSpacer(4.dp)

        SGText(
            text = "댓글 내용이 들어가면 될 거 같아요 여기까지면 되지 않을까요?",
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = 0.em,
            )
        )

        HeightSpacer(8.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = MyIconPack.IconNonFillHeart,
                    contentDescription = "heart",
                    tint = SGColor.primaryA.copy(alpha = 0.9f),
                    modifier = Modifier.size(
                        width = 16.dp,
                        height = 16.dp
                    )
                )

                WidthSpacer(4.dp)

                SGText(
                    text = "0",
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        fontFamily = SGTypography.poppins,
                        letterSpacing = 0.em,
                    )
                )

                WidthSpacer(32.dp)

                SGText(
                    text = "답글 달기",
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryA.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        fontFamily = SGTypography.nanumSquareNeo,
                        letterSpacing = 0.em,
                    )
                )
            }

            SGText(
                text = "15시간 전",
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostCommentScreenBodyPreview() {
    Box(modifier = Modifier.background(SGColor.white)) {
        HomePostCommentScreenBody()
    }
}