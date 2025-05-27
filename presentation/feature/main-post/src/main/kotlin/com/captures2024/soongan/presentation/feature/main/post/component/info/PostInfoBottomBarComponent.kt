package com.captures2024.soongan.presentation.feature.main.post.component.info

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGIconButton
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGDimension
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.designsystem.ui.util.extension.toKM
import com.captures2024.soongan.presentation.feature.main.post.R

@Composable
internal fun PostInfoBottomBarComponent(
    isLiked: Boolean,
    likeCount: Int,
    modifier: Modifier = Modifier,
    onClickMenu: () -> Unit,
    onClickHeart: () -> Unit,
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
                offsetY = (-2).dp,
            )
            .background(color = SGColor.Grayscale.white)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SGIconButton(
            onClick = onClickMenu,
        ) {
            Icon(
                imageVector = MyIconPack.IconNonFillMenu,
                contentDescription = stringResource(R.string.menu_description),
                tint = SGColor.primaryA,
                modifier = Modifier.size(
                    width = 4.dp,
                    height = 20.dp,
                ),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Crossfade(
                targetState = isLiked,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearEasing,
                ),
                label = "isLiked icon ease out animation",
            ) { isLiked ->
                SGIconButton(
                    onClick = onClickHeart,
                ) {
                    Icon(
                        imageVector = when (isLiked) {
                            true -> MyIconPack.IconFillHeart
                            false -> MyIconPack.IconNonFillHeart
                        },
                        contentDescription = stringResource(R.string.heart_description),
                        tint = SGColor.Grayscale.black100,
                        modifier = Modifier.size(
                            width = 24.dp,
                            height = 21.dp,
                        ),
                    )
                }
            }

            WidthSpacer(8.dp)

            SGText(
                text = likeCount.toKM(),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 12.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-2).em,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoBottomBarComponent_Default() {
    SGTheme {
        PostInfoBottomBarComponent(
            isLiked = false,
            likeCount = 0,
            onClickMenu = {},
            onClickHeart = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewPostInfoBottomBarComponent_BigNum() {
    SGTheme {
        PostInfoBottomBarComponent(
            isLiked = true,
            likeCount = 1_234_567,
            onClickMenu = {},
            onClickHeart = {},
        )
    }
}
