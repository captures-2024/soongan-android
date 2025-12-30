package com.captures2024.soongan.presentation.feature.signup.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.signup.R
import com.captures2024.soongan.presentation.feature.signup.component.SignUpBottomComponent
import com.captures2024.soongan.presentation.feature.signup.component.SignUpTopComponent
import com.captures2024.soongan.presentation.viewmodel.sign.SignUpViewModel

@Composable
internal fun SignUpScreen(
    state: SignUpViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onNicknameValueChanged: (String) -> Unit,
    onBirthValueChanged: (String) -> Unit,
    onConfirmNickname: () -> Unit,
    onConfirmBirth: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFFAFAF8))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() },
            ),
    ) {
        SignUpTopComponent(
            content = stringResource(R.string.sign_up_title),
            onClick = onClickBack,
        )

        when (state.nicknameState.isRemoteSuccess) {
            false -> InputNicknameScreen(
                state = state.nicknameState,
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                onNicknameValueChanged = onNicknameValueChanged,
            )

            true -> InputBirthScreen(
                nickname = state.nicknameState.nickname,
                state = state.birthState,
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                onBrithValueChanged = onBirthValueChanged,
            )
        }

        SignUpBottomComponent(
            description = stringResource(R.string.sign_up_description),
            content = stringResource(R.string.sign_up_content),
            enabled = when {
                state.nicknameState.isRemoteSuccess -> state.birthState.isValid == Validation.BirthYearValidState.Success

                else -> state.nicknameState.isValid == Validation.NicknameValidState.Success
            },
            modifier = Modifier.fillMaxWidth()
                .imePadding(),
            onClick = {
                when (state.nicknameState.isRemoteSuccess) {
                    true -> onConfirmBirth()
                    false -> onConfirmNickname()
                }
            },
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewSignUpScreen() {
    SGBackground {
        SignUpScreen(
            state = SignUpViewModel.State(
                nicknameState = SignUpViewModel.State.NicknameState(
                    nickname = AppConst.EMPTY_STRING,
                    maxNicknameLength = AppConst.Sign.SignUp.MAX_NICKNAME_LENGTH,
                    isDuplicatedNickname = false,
                    isRemoteSuccess = false,
                ),
                birthState = SignUpViewModel.State.BirthState(
                    birthYear = "2005",
                    maxBirthLength = AppConst.Sign.SignUp.MAX_BIRTH_LENGTH,
                ),
            ),
            onClickBack = {},
            onNicknameValueChanged = {},
            onBirthValueChanged = {},
            onConfirmNickname = {},
            onConfirmBirth = {},
        )
    }
}
