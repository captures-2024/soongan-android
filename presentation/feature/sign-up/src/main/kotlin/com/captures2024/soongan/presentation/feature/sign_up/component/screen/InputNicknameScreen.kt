package com.captures2024.soongan.presentation.feature.sign_up.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.background.SGBackground
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldFormState
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldTypeForm
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.feature.sign_up.R
import com.captures2024.soongan.presentation.viewmodel.sign.SignUpViewModel

@Composable
internal fun InputNicknameScreen(
    state: SignUpViewModel.State.NicknameState,
    modifier: Modifier = Modifier,
    onNicknameValueChanged: (String) -> Unit,
) {
    val windowInfo = LocalWindowInfo.current
    val focusRequester: FocusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(modifier = Modifier.padding(start = 12.dp)) {
            SGText(
                text = stringResource(R.string.nickname_title),
                style = getSGNonScaleTextStyle(
                    color = SGColor.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                ),
            )
        }

        HeightSpacer(4.dp)

        SGTextFieldTypeForm(
            value = state.nickname,
            onValueChange = onNicknameValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            enabled = state.isRemoteSuccess.not(),
            textStyle = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
            hint = stringResource(R.string.nickname_hint),
            state = when {
                state.isDuplicatedNickname || state.isValid == Validation.NicknameValidState.Regex -> SGTextFieldFormState.Error
                state.isValid == Validation.NicknameValidState.Success -> SGTextFieldFormState.Success
                else -> SGTextFieldFormState.Default
            },
        )

        HeightSpacer(12.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SGText(
                text = when {
                    state.isDuplicatedNickname -> stringResource(R.string.nickname_error_description_duplicated)
                    state.isValid == Validation.NicknameValidState.Regex -> stringResource(R.string.nickname_error_description_regex)
                    else -> stringResource(R.string.nickname_default_description)
                },
                style = getSGNonScaleTextStyle(
                    color = when {
                        state.isDuplicatedNickname || state.isValid == Validation.NicknameValidState.Regex -> SGColor.negative
                        else -> SGColor.hintGray
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.pretendard,
                ),
            )

            SGText(
                text = "${state.nickname.length}/${state.maxNicknameLength}",
                style = getSGNonScaleTextStyle(
                    color = SGColor.hintGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.pretendard,
                ),
            )
        }
    }

    LaunchedEffect(windowInfo, state.isRemoteSuccess) {
        snapshotFlow { windowInfo.isWindowFocused }
            .collect { isWindowFocused ->
                if (!state.isRemoteSuccess && isWindowFocused) {
                    focusRequester.requestFocus()
                }
            }
    }
}

@DevicePreviews
@Composable
private fun PreviewInputNicknameScreen() {
    SGBackground {
        InputNicknameScreen(
            state = SignUpViewModel.State.NicknameState(
                nickname = AppConst.EMPTY_STRING,
                maxNicknameLength = AppConst.Sign.SignUp.MAX_NICKNAME_LENGTH,
                isDuplicatedNickname = false,
                isRemoteSuccess = false,
            ),
            onNicknameValueChanged = {},
        )
    }
}
