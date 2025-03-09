package com.captures2024.soongan.core.designsystem.ui.component.text.field

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillCheck
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillError
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme

enum class SGTextFieldFormState {
    Default,
    Success,
    Error,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SGTextFieldTypeForm(
    value: String,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    state: SGTextFieldFormState = SGTextFieldFormState.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundShape = RoundedCornerShape(8.dp)
    val hintTextStyle = textStyle.copy(color = SGColor.buttonDisableGray)

    SGTextField(
        value = value,
        textStyle = textStyle,
        onValueChange = onValueChange,
        modifier = modifier.heightIn(min = 48.dp)
            .border(
                width = 1.dp,
                color = when (state) {
                    SGTextFieldFormState.Default,
                    SGTextFieldFormState.Success -> SGColor.black

                    SGTextFieldFormState.Error -> SGColor.negative
                },
                shape = backgroundShape,
            ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = {
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = it,
                singleLine = true,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                trailingIcon = @Composable {
                    Icon(
                        imageVector = when (state) {
                            SGTextFieldFormState.Default,
                            SGTextFieldFormState.Success -> MyIconPack.IconFillCheck

                            SGTextFieldFormState.Error -> MyIconPack.IconFillError
                        },
                        contentDescription = "state",
                        tint = when (state) {
                            SGTextFieldFormState.Default -> SGColor.buttonDisableGray

                            SGTextFieldFormState.Success -> SGColor.positive

                            SGTextFieldFormState.Error -> SGColor.negative
                        },
                    )
                },
                placeholder = @Composable {
                    SGText(
                        text = hint,
                        style = hintTextStyle,
                        maxLines = 1,
                    )
                },
                interactionSource = interactionSource,
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                    start = 16.dp,
                    end = 0.dp,
                    top = 12.dp,
                    bottom = 12.dp,
                ),
                shape = backgroundShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SGColor.white,
                    unfocusedContainerColor = SGColor.white,
                    focusedTextColor = SGColor.primaryA,
                    unfocusedTextColor = SGColor.primaryA,
                    focusedIndicatorColor = SGColor.transparent,
                    unfocusedIndicatorColor = SGColor.transparent,
                    disabledIndicatorColor = SGColor.transparent,
                ),
            )
        },
    )
}

@Preview
@Composable
private fun PreviewSGTextFieldTypeForm_hint() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextFieldTypeForm(
                value = "",
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = {},
                hint = "텍스트를 입력해주세요",
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSGTextFieldTypeForm_default() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextFieldTypeForm(
                value = "텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트",
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = {},
                hint = "텍스트를 입력해주세요",
            )
        }
    }
}


@Preview
@Composable
private fun PreviewSGTextFieldTypeForm_success() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextFieldTypeForm(
                value = "텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트",
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = {},
                hint = "텍스트를 입력해주세요",
                state = SGTextFieldFormState.Success,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSGTextFieldTypeForm_error() {
    SGTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SGColor.white)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            SGTextFieldTypeForm(
                value = "텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트",
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
                onValueChange = {},
                hint = "텍스트를 입력해주세요",
                state = SGTextFieldFormState.Error,
            )
        }
    }
}
