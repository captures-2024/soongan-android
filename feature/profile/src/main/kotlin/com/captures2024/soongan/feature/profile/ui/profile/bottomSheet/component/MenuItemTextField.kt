package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.nonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

@Composable
internal fun MenuItemTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    maxInputLength: Int = 20,
) {
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= maxInputLength + 1) onValueChange(newValue)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = SGColor.primaryA,
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
                        letterSpacing = (-0.05).em,
                        lineHeight = 24.sp
                    )
                }
                innerTextField()
            }
        }
    )
}


@DevicePreviews
@Composable
private fun ProfileOutlinedTextFieldWithErrorPreview() {
    MenuItemTextField(
        value = "",
        onValueChange = {},
        maxInputLength = 20,
        placeholder = "회원탈퇴"
    )
}