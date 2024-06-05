package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.component.CustomBasicTextField
import com.captures2024.soongan.core.designsystem.component.CustomBasicTextFieldState
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.Negative
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryB
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.InputBirthYearUIState
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun InputBirthYearBodyScreen(
    modifier: Modifier = Modifier,
    state: InputBirthYearUIState,
    isValid: Validation.BirthYearValidState,
    onValueChange: (String) -> Unit = {}
) {
    val windowInfo = LocalWindowInfo.current
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = PrimaryA),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                NonScaleText(
                    text = stringResource(id = R.string.input_birth_year_nickname_title),
                    color = Color(0xFFCACACA),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                NonScaleText(
                    text = state.nickname,
                    color = PrimaryB,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            CustomBasicTextField(
                modifier = Modifier.focusRequester(focusRequester),
                value = state.birthYear,
                title = stringResource(id = R.string.input_birth_year_title),
                hint = stringResource(id = R.string.input_birth_year_input_form_hint_text),
                isValid = when (isValid) {
                    Validation.BirthYearValidState.Success -> CustomBasicTextFieldState.Valid
                    Validation.BirthYearValidState.Regex -> CustomBasicTextFieldState.NonValid
                    Validation.BirthYearValidState.Length -> CustomBasicTextFieldState.Init
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                onValueChange = onValueChange
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                NonScaleText(
                    text = when (isValid) {
                        Validation.BirthYearValidState.Regex -> stringResource(id = R.string.input_birth_fail_hint_text)
                        else -> stringResource(id = R.string.input_birth_default_hint_text)
                    },
                    color = when (isValid) {
                        Validation.BirthYearValidState.Regex -> Negative
                        else -> Color(0xFFCACACA)
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    LaunchedEffect(windowInfo) {
        snapshotFlow { windowInfo.isWindowFocused }.collect { isWindowFocused ->
            if (isWindowFocused) {
                focusRequester.requestFocus()
            }
        }
    }
}

@DevicePreviews
@Composable
private fun InputBirthYearBodyScreenPreview() {
    InputBirthYearBodyScreen(
        state = InputBirthYearUIState.ValueChanged(
            nickname = "테스트",
            birthYear = ""
        ),
        isValid = Validation.BirthYearValidState.Length
    )
}