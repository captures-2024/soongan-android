package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameUIState

@Composable
internal fun InputNicknameScreen(
    state: NicknameUIState,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit = {},
    onChangedNickname: (String) -> Unit = {},
    onClickConfirm: () -> Unit = {}
) {
    Scaffold(
        topBar = @Composable {
            SignUpTopBar(onClickBack = onClickBack)
        },
        bottomBar = @Composable {
            SignUpBottomBar(
                modifier = Modifier.imePadding(),
                title = stringResource(id = R.string.btn_nickname_input_title),
                enabled = when (state.isValidNickname) {
                    Validation.NicknameValidState.Success -> true
                    else -> false
                },
                onClick = onClickConfirm
            )
        }
    ) { paddingValues ->
        InputNicknameBodyScreen(
            modifier = modifier.padding(paddingValues),
            state = state,
            onChangedNickname = onChangedNickname
        )
    }
}

@DevicePreviews
@Composable
private fun InputNicknameScreenPreview() {
    InputNicknameScreen(
        state = NicknameUIState()
    )
}

@DevicePreviews
@Composable
private fun InputNicknameScreenInputPreview() {
    InputNicknameScreen(
        state = NicknameUIState(nickname = "test")
    )
}