package com.captures2024.soongan.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTheme
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.theme.innerShadow

@Composable
fun SGButtonType1(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val backgroundShape = RoundedCornerShape(8.dp)

    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp),
        enabled = enabled,
        shape = backgroundShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = SGColor.primaryA,
            contentColor = SGColor.primaryB,
            disabledContainerColor = SGColor.buttonDisableGray,
            disabledContentColor = SGColor.white,
        ),
        content = content,
    )
}

@Composable
fun SGButtonType2(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val backgroundShape = RoundedCornerShape(8.dp)

    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp)
            .let {
                return@let when (enabled) {
                    true -> it.dropShadow(
                        shape = backgroundShape,
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                    )

                    false -> it.innerShadow(
                        shape = backgroundShape,
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                    )
                }
            },
        enabled = enabled,
        shape = backgroundShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = SGColor.accent,
            contentColor = SGColor.primaryA,
            disabledContainerColor = SGColor.buttonDisableGray,
            disabledContentColor = SGColor.white,
        ),
        content = content,
    )
}

@Preview
@Composable
private fun PreviewSGButtonType1_enable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGButtonType1(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun PreviewSGButtonType1_disable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGButtonType1(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun PreviewSGButtonType2_enable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGButtonType2(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun PreviewSGButtonType2_disable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGButtonType2(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            ) {

            }
        }
    }
}
