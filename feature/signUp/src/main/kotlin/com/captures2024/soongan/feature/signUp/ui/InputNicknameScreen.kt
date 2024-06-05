package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.InputNickNameUIState
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun InputNicknameScreen(
    state: InputNickNameUIState,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onChangedNickname: (String) -> Unit = {},
    onClickCheckDuplication: () -> Unit = {}
) {
    val isValid = Validation.isValidNickname(nickname = state.nickname)

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
        InputNicknameBodyScreen(
            modifier = modifier.padding(paddingValues),
            state = state,
            isValid = isValid,
            onChangedNickname = onChangedNickname
        )
    }
}

@DevicePreviews
@Composable
private fun InputNicknameScreenPreview() {
    InputNicknameScreen(
        state = InputNickNameUIState.Init
    )
}