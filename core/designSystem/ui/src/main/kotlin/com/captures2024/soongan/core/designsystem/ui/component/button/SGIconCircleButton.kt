package com.captures2024.soongan.core.designsystem.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillCircleQuestion
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.dropShadow
import com.captures2024.soongan.core.designsystem.ui.theme.innerShadow

@Composable
fun SGIconCircleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val backgroundShape = CircleShape

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .size(44.dp)
            .let {
                return@let when (pressed) {
                    true -> it

                    false -> it.dropShadow(
                        shape = backgroundShape,
                        blur = 4.dp,
                        offsetX = 0.dp,
                        offsetY = 2.dp
                    )
                }
            }
            .background(
                color = SGColor.white,
                shape = backgroundShape,
            )
            .let {
                return@let when (pressed) {
                    true -> it.innerShadow(
                        shape = backgroundShape,
                        blur = 4.dp,
                        offsetX = 2.dp,
                        offsetY = 2.dp
                    )

                    false -> it
                }
            }
            .clip(backgroundShape)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

@Composable
fun SGIconCircleButton(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    color: Color = SGColor.primaryA,
    iconWidth: Dp = 20.dp,
    iconHeight: Dp = 20.dp,
    onClick: () -> Unit,
) {
    SGIconCircleButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(
                width = iconWidth,
                height = iconHeight,
            ),
            tint = color,
        )
    }
}


@Preview
@Composable
private fun PreviewSGIconButton() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGIconCircleButton(
                imageVector = MyIconPack.IconNonFillCircleQuestion,
                contentDescription = null,
                onClick = {},
            )
        }
    }
}
