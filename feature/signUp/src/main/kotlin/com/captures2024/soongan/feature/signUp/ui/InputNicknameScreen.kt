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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.captures2024.soongan.feature.signUp.InputNickNameUIState
import com.captures2024.soongan.feature.signUp.R
import kotlinx.coroutines.delay

@Composable
internal fun InputNicknameScreen(
    state: InputNickNameUIState,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onChangedNickname: (String) -> Unit = {},
    onClickCheckDuplication: () -> Unit = {}
) {
    val isValid = Validation.isValidNickname(nickname = state.nickname)

    val windowInfo = LocalWindowInfo.current
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = @Composable {
            SignUpTopBar(onClickBack = onClickBack)
        },
        bottomBar = @Composable {
            SignUpBottomBar(
                modifier = Modifier.imePadding(),
                title = stringResource(id = R.string.btn_nickname_input_title),
                enabled = when (isValid) {
                    Validation.NicknameValidState.Success -> true
                    else -> false
                },
                onClick = onClickCheckDuplication
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = PrimaryA)
                .padding(paddingValues),
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
                    isValid = when {
                        state is InputNickNameUIState.Error || isValid == Validation.NicknameValidState.Regex -> CustomBasicTextFieldState.NonValid
                        isValid == Validation.NicknameValidState.Success -> CustomBasicTextFieldState.Valid
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
                            state is InputNickNameUIState.Error -> stringResource(id = R.string.input_nickname_fail_duplication_hint_text)
                            isValid == Validation.NicknameValidState.Regex -> stringResource(id = R.string.input_nickname_fail_regex_hint_text)
                            else -> stringResource(id = R.string.input_nickname_default_hint_text)
                        },
                        color = when {
                            state is InputNickNameUIState.Error || isValid == Validation.NicknameValidState.Regex -> Negative
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
private fun InputNicknameScreenPreview() {
    InputNicknameScreen(
        state = InputNickNameUIState.Init
    )
}

@DevicePreviews
@Composable
private fun InputNicknameScreenFirstCasePreview() {
    InputNicknameScreen(
        state = InputNickNameUIState.ValueChanged(nickname = "abcd")
    )
}

@DevicePreviews
@Composable
private fun InputNicknameScreenSecondCasePreview() {
    InputNicknameScreen(
        state = InputNickNameUIState.ValueChanged(nickname = "ab")
    )
}