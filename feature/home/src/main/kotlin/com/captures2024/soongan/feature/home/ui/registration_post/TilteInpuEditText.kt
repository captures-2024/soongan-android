package com.captures2024.soongan.feature.home.ui.registration_post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.dropShadow
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

private const val MAX_INPUT_LENGTH = 15

@Composable
internal fun TitleInputEditText(
    value: String,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(horizontalAlignment = Alignment.End) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.width(289.dp)
                .height(40.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NanumSquareNeoFontFamily,
                lineHeight = 16.sp,
                color = SGColor.primaryA,
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize()
                        .dropShadow(
                            shape = RectangleShape,
                            color = SGColor.black.copy(0.25f),
                            offsetX = 0.dp,
                            offsetY = 2.dp,
                            blur = 4.dp,
                            spread = 0.dp,
                        )
                        .background(
                            color = SGColor.white,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    innerTextField()

                    if (value.isEmpty()) {
                        NonScaleText(
                            text = "제목을 입력해주세요.",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = NanumSquareNeoFontFamily,
                            lineHeight = 16.sp,
                            color = SGColor.primaryA.copy(alpha = 0.3f)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        HeightSpacer(8.dp)
        Row(
            modifier = Modifier.padding(end = 4.dp),
        ) {
            NonScaleText(
                text = "${value.length}/$MAX_INPUT_LENGTH",
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = NanumSquareNeoFontFamily,
                lineHeight = 8.sp,
                color = SGColor.black,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun TitleInputEditTextPreview() {
    TitleInputEditText(
        value = "어쩌구저쩌어쩌구저쩌어쩌구저쩌",
        onValueChange = {}
    )
}

@DevicePreviews
@Composable
private fun TitleInputEditTextEmptyPreview() {
    TitleInputEditText(
        value = "",
        onValueChange = {}
    )
}