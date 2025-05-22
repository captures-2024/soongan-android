package com.captures2024.soongan.presentation.feature.main.post.component.report

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography

@Composable
internal fun ReportReasonTextFieldComponent(
    text: String,
    onValueChanged: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SGColor.Grayscale.white,
                shape = RoundedCornerShape(8.dp),
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = SGColor.Grayscale.black100,
                ),
                shape = RoundedCornerShape(8.dp),
            )
            .height(160.dp),
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(160.dp),
            value = text,
            onValueChange = { newText ->
                if (newText.length <= maxLength) {
                    onValueChanged(newText)
                }
            },
            textStyle = getSGNonScaleTextStyle(
                color = SGColor.Grayscale.black100,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions { focusManager.clearFocus() },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = SGColor.Grayscale.white,
                            shape = RoundedCornerShape(8.dp),
                        ),
                ) {
                    if (text.isEmpty()) {
                        SGText(
                            text = "신고 사유를 입력해주세요",
                            style = getSGNonScaleTextStyle(
                                color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                fontFamily = SGTypography.pretendard,
                                letterSpacing = (-5).em,
                            ),
                        )
                    }
                    innerTextField()
                }
            },
        )

        SGText(
            text = "${text.length}/$maxLength",
            style = getSGNonScaleTextStyle(
                color = when (text.length < maxLength) {
                    true -> SGColor.Grayscale.black100
                    false -> SGColor.negative
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.sp,
            ),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd),
        )
    }
}
