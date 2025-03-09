package com.captures2024.soongan.feature.profile.ui.edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldFormState
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldTypeForm
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews

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

        Box(
            modifier = modifier.padding(start = 12.dp),
        ) {
            SGText(
                text = detailTitle,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 8.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                ),
            )
        }

        HeightSpacer(4.dp)

        SGTextFieldTypeForm(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxInputLength + 1) {
                    onValueChange(newValue)
                }
            },
            textStyle = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
            hint = placeholder,
            modifier = Modifier.fillMaxWidth(),
            state = when(isInvalid) {
                true -> SGTextFieldFormState.Error

                false -> SGTextFieldFormState.Default
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )

        HeightSpacer(4.dp)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SGText(
                text = hint,
                style = getSGNonScaleTextStyle(
                    color = SGColor.negative,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 8.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                ),
            )

            SGText(
                text = "${value.length}/$maxInputLength",
                style = getSGNonScaleTextStyle(
                    color = when {
                        value.length <= maxInputLength -> SGColor.black

                        else -> SGColor.negative
                    },
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 8.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                ),
            )
        }
    }
}

@DevicePreviews
@Composable
private fun ProfileOutlinedTextFieldPreview() {
    ProfileOutlinedTextField(
        value = "asdads",
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