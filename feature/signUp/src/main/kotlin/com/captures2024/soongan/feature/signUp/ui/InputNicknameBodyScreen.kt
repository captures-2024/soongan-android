package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.component.CustomBasicTextField
import com.captures2024.soongan.core.designsystem.component.CustomBasicTextFieldState
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.Negative
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameUIState

@Composable
internal fun InputNicknameBodyScreen(
    modifier: Modifier = Modifier,
    state: NicknameUIState,
    onChangedNickname: (String) -> Unit = {},
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
            CustomBasicTextField(
                modifier = Modifier.focusRequester(focusRequester),
                value = state.nickname,
                title = stringResource(id = R.string.input_nickname_input_title),
                hint = stringResource(id = R.string.input_nickname_input_form_hint_text),
                isValid = when {
                    state.isDuplicatedNickname || state.isValidNickname == Validation.NicknameValidState.Regex -> CustomBasicTextFieldState.NonValid
                    state.isValidNickname == Validation.NicknameValidState.Success -> CustomBasicTextFieldState.Valid
                    else -> CustomBasicTextFieldState.Init
                },
                onValueChange = onChangedNickname
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                NonScaleText(
                    text = when {
                        state.isDuplicatedNickname -> stringResource(id = R.string.input_nickname_fail_duplication_hint_text)
                        state.isValidNickname == Validation.NicknameValidState.Regex -> stringResource(id = R.string.input_nickname_fail_regex_hint_text)
                        else -> stringResource(id = R.string.input_nickname_default_hint_text)
                    },
                    color = when {
                        state.isDuplicatedNickname || state.isValidNickname == Validation.NicknameValidState.Regex -> Negative
                        else -> Color(0xFFCACACA)
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                NonScaleText(
                    text = "${state.nickname.length}/10",
                    color = Color(0xFFCACACA),
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
private fun InputNicknameBodyScreenPreview() {
    InputNicknameBodyScreen(
        state = NicknameUIState(nickname = "abcd"),
        onChangedNickname = {}
    )
}

@DevicePreviews
@Composable
private fun InputNicknameBodyScreenFailPreview() {
    InputNicknameBodyScreen(
        state = NicknameUIState(nickname = "@!abcd"),
        onChangedNickname = {}
    )
}