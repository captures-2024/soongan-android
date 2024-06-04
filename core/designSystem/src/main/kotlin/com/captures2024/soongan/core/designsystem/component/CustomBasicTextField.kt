package com.captures2024.soongan.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconCircleCheck
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconCircleCheckFail
import com.captures2024.soongan.core.designsystem.theme.Negative
import com.captures2024.soongan.core.designsystem.theme.Positive
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews

enum class CustomBasicTextFieldState {
    Init, Valid, NonValid
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBasicTextField(
    modifier: Modifier = Modifier,
    value: String,
    title: String,
    hint: String = "",
    isValid: CustomBasicTextFieldState = CustomBasicTextFieldState.Init,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    singleLine: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row {
            Spacer(modifier = Modifier.width(12.dp))
            NonScaleText(
                text = title,
                color = PrimaryB ,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(

            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PrimaryB,
                    shape = shape
                )
                .border(
                    width = 2.dp,
                    color = when (isValid) {
                        CustomBasicTextFieldState.NonValid -> Negative
                        else -> Color(0xFFDBDBDB)
                    },
                    shape = shape
                ),
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = singleLine
        ) {
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = it,
                singleLine = true,
                enabled = enabled,
                visualTransformation = VisualTransformation.None,
                trailingIcon = @Composable {
                    Icon(
                        imageVector = when (isValid) {
                            CustomBasicTextFieldState.NonValid -> MyIconPack.IconCircleCheckFail
                            else -> MyIconPack.IconCircleCheck
                        },
                        contentDescription = "",
                        tint = when (isValid) {
                            CustomBasicTextFieldState.Init -> Color(0xFFDBDBDB)
                            CustomBasicTextFieldState.Valid -> Positive
                            CustomBasicTextFieldState.NonValid -> Negative
                        }
                    )
                },
                placeholder = @Composable {
                    NonScaleText(
                        text = hint,
                        color = Color(0xFFCACACA),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                },
                interactionSource = interactionSource,
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                    start = 16.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
                shape = shape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
        }
    }
}

@DevicePreviews
@Composable
private fun CustomBasicTextFieldInitPreview() {
    CustomBasicTextField(
        value = "",
        title = "닉네임"
    ) {

    }
}

@DevicePreviews
@Composable
private fun CustomBasicTextFieldValidPreview() {
    CustomBasicTextField(
        value = "",
        title = "닉네임",
        isValid = CustomBasicTextFieldState.Valid
    ) {

    }
}

@DevicePreviews
@Composable
private fun CustomBasicTextFieldNonValidPreview() {
    CustomBasicTextField(
        value = "",
        title = "닉네임",
        isValid = CustomBasicTextFieldState.NonValid
    ) {

    }
}