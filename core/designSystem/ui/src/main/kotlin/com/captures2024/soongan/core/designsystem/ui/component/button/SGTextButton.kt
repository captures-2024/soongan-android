package com.captures2024.soongan.core.designsystem.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme

@Composable
fun SGTextButtonType1(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    SGButtonType1(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = SGColor.white,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                letterSpacing = (-5).em,
                fontFamily = SGTypography.nanumSquareNeo,
            ),
        )
    }
}

@Composable
fun SGTextButtonType2(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    SGButtonType2(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        SGText(
            text = text,
            style = getSGNonScaleTextStyle(
                color = when (enabled) {
                    true -> SGColor.primaryA

                    false -> SGColor.white
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                letterSpacing = (-5).em,
                fontFamily = SGTypography.nanumSquareNeo,
            ),
        )
    }
}

@Preview
@Composable
private fun PreviewSGTextButtonType1_disable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextButtonType1(
                text = "버튼",
                enabled = false,
            ) {}
        }
    }
}

@Preview
@Composable
private fun PreviewSGTextButtonType1_enable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextButtonType1(
                text = "버튼",
                enabled = true,
            ) {}
        }
    }
}

@Preview
@Composable
private fun PreviewSGTextButtonType2_disable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextButtonType2(
                text = "버튼",
                enabled = false,
            ) {}
        }
    }
}

@Preview
@Composable
private fun PreviewSGTextButtonType2_enable() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextButtonType2(
                text = "버튼",
                enabled = true,
            ) {}
        }
    }
}
