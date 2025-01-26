package com.captures2024.soongan.feature.profile.ui.edit.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.nonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun ProfileOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    detailTitle: String = "",
    placeholder: String = "",
    isInvalid: Boolean = false,
    hint: String = "",
    maxInputLength: Int = 20,
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        Box(modifier = modifier.padding(start = 12.dp)) {
            NonScaleText(
                text = detailTitle,
                fontSize = 8.sp,
                color = SGColor.black,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            )
        }
        HeightSpacer(4.dp)
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxInputLength + 1) onValueChange(newValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = if (isInvalid) SGColor.negative else SGColor.primaryA.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                ),
            textStyle = nonScaleTextStyle(
                fontSize = 18.sp,
                fontFamily = NanumSquareNeoFontFamily,
                fontWeight = FontWeight.Normal,
                letterSPacing = (-5).em,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        NonScaleText(
                            text = placeholder,
                            fontSize = 18.sp,
                            color = SGColor.primaryA.copy(alpha = 0.3f),
                            fontWeight = FontWeight.Normal,
                            fontFamily = NanumSquareNeoFontFamily,
                            letterSpacing = (-5).em,
                            lineHeight = 24.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
        HeightSpacer(4.dp)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NonScaleText(
                text = hint,
                fontSize = 8.sp,
                color = SGColor.negative,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp,
                lineHeight = 8.sp
            )
            NonScaleText(
                text = "${value.length}/$maxInputLength",
                fontSize = 8.sp,
                color = if (value.length <= maxInputLength) SGColor.black else SGColor.negative,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp,
                lineHeight = 8.sp
            )
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileOutlinedTextFieldPreview() {
    ProfileOutlinedTextField(
        value = "",
        onValueChange = {},
        isInvalid = false,
        hint = "",
        maxInputLength = 20,
        detailTitle = "닉네임은 한글, 영문, 숫자만 입력해주세요",
        placeholder = "닉네임은 한글, 영문, 숫자만 입력해주세요"
    )
}

@DevicePreviews
@Composable
private fun ProfileOutlinedTextFieldWithErrorPreview() {
    ProfileOutlinedTextField(
        value = "",
        onValueChange = {},
        isInvalid = true,
        hint = "에러메시지",
        maxInputLength = 20,
        detailTitle = "닉네임은 한글, 영문, 숫자만 입력해주세요",
        placeholder = "닉네임은 한글, 영문, 숫자만 입력해주세요"
    )
}