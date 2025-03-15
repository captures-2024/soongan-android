package com.captures2024.soongan.feature.home.ui.post

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.button.SGIconButton
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGDimension
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.designsystem.util.extension.toKM

@Composable
internal fun HomePostScreenBottomBar(
    isLiked: Boolean,
    likeCount: Int,
    commentCount: Int,
    modifier: Modifier = Modifier,
    onClickMenu: () -> Unit = {},
    onClickHeart: () -> Unit = {},
    onClickComment: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SGDimension.bottomBarHeight)
            .dropShadow(
                shape = RoundedCornerShape(0.dp),
                color = SGColor.black.copy(alpha = 0.3f),
                blur = 4.dp,
                offsetX = 0.dp,
                offsetY = (-2).dp
            )
            .background(color = SGColor.white)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SGIconButton(
            onClick = onClickMenu
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = "menu",
                tint = SGColor.primaryA,
                modifier = Modifier.size(
                    width = 4.dp,
                    height = 20.dp
                )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Crossfade(
                targetState = isLiked,
                animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                label = "isLiked icon ease out animation"
            ) { isLiked ->
                val icon = if (isLiked) MyIconPack.IconFillHeart else MyIconPack.IconNonFillHeart
                SGIconButton(
                    onClick = onClickHeart
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "heart",
                        tint = SGColor.primaryA,
                        modifier = Modifier
                            .size(
                                width = 24.dp,
                                height = 21.dp
                            )
                    )
                }
            }

            WidthSpacer(8.dp)

            SGText(
                text = likeCount.toKM(),
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 12.sp,
                    fontFamily = SGTypography.poppins,
                    letterSpacing = (-2).em,
                )
            )

            // TODO 댓글 1차 MVP 스펙아웃
//            Spacer(modifier = Modifier.width(31.dp))
//            Icon(
//                imageVector = MyIconPack.IconNonFillComment,
//                contentDescription = "comment",
//                tint = SGColor.primaryA,
//                modifier = Modifier
//                    .size(
//                        width = 24.dp,
//                        height = 24.dp
//                    )
//                    .clickable(
//                        onClick = onClickComment
//                    ),
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            NonScaleText(
//                text = commentCount.toKM(),
//                color = SGColor.primaryA,
//                fontSize = 12.sp,
//                fontWeight = FontWeight.Light
//            )
            WidthSpacer(15.dp)
        }
    }
}

@DevicePreviews
@Composable
private fun HomePostScreenBottomBarPreview() {
    HomePostScreenBottomBar(
        isLiked = false,
        likeCount = 1_234_567,
        commentCount = 0,
    )
}