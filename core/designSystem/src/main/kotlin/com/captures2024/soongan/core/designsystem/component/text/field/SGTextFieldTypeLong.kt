package com.captures2024.soongan.core.designsystem.component.text.field

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTheme
import com.captures2024.soongan.core.designsystem.theme.SGTypography

@Composable
fun SGTextFieldTypeLong(
    value: String,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    hint: String,
    placeholderText: String,
    placeholderStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    val backgroundShape = RoundedCornerShape(8.dp)
    val hintTextStyle = textStyle.copy(color = SGColor.buttonDisableGray)

    Column(
        modifier = modifier
            .clip(backgroundShape)
            .border(
                width = 1.dp,
                color = SGColor.primaryA,
                shape = backgroundShape,
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Box {
            SGTextField(
                value = value,
                textStyle = textStyle,
                onValueChange = onValueChange
            )
            if (value.isEmpty()) {
                SGText(
                    text = hint,
                    style = hintTextStyle,
                )
            }
        }

        SGText(
            text = placeholderText,
            style = placeholderStyle,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun PreviewSGTextFieldTypeLong_hint() {
    val value = ""
    val maxSize = 200

    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextFieldTypeLong(
                value = value,
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = {},
                hint = "텍스트를 입력해주세요",
                placeholderText = "${value.length}/$maxSize",
                placeholderStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                    textAlign = TextAlign.End,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSGTextFieldTypeLong_default() {
    val value = "텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트"
    val maxSize = 200

    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextFieldTypeLong(
                value = value,
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = {},
                hint = "텍스트를 입력해주세요",
                placeholderText = "${value.length}/$maxSize",
                placeholderStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = 0.em,
                    textAlign = TextAlign.End,
                ),
            )
        }
    }
}
