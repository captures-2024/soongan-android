package com.captures2024.soongan.core.designsystem.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillCircleQuestion
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme

@Composable
fun SGIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clickable(
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

@Composable
fun SGIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    color: Color = SGColor.primaryA,
    onClick: () -> Unit,
) {
    SGIconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
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
            SGIconButton(
                imageVector = MyIconPack.IconNonFillCircleQuestion,
                contentDescription = null,
                onClick = {},
            )
        }
    }
}
