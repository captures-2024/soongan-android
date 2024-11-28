package com.captures2024.soongan.feature.profile.ui.edit.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.Negative
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

private const val MAX_INPUT_LENGTH = 20

@Composable
internal fun ProfileOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    placeHolder: String = "",
) {
    Column(modifier = modifier) {
        Box(modifier = modifier.padding(start = 12.dp)) {
            NonScaleText(
                text = hint,
                fontSize = 8.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            )
        }
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= MAX_INPUT_LENGTH) onValueChange(newValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = PrimaryA.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(start = 14.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        NonScaleText(
                            text = placeHolder,
                            fontSize = 16.sp,
                            color = PrimaryA.copy(alpha = 0.3f),
                            fontWeight = FontWeight.Bold,
                            fontFamily = NanumSquareNeoFontFamily,
                            letterSpacing = 0.sp,
                            lineHeight = 16.sp
                        )
                    }

                    innerTextField()
                }
            }
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            NonScaleText(
                text = "${value.length}/$MAX_INPUT_LENGTH",
                fontSize = 8.sp,
                color = if (value.length < MAX_INPUT_LENGTH) Color.Black else Negative,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.sp
            )
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileOutlinedTextFieldPreview() {
    Surface {
        ProfileOutlinedTextField(
            value = "",
            onValueChange = {},
            hint = "닉네임은 한글, 영문, 숫자만 입력해주세요",
            placeHolder = "닉네임은 한글, 영문, 숫자만 입력해주세요"
        )

    }
}