package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldFormState
import com.captures2024.soongan.core.designsystem.ui.component.text.field.SGTextFieldTypeForm
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.sign.NicknameViewModel
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun NicknameScreen(
    intent: (NicknameViewModel.Intent) -> Unit,
    state: NicknameViewModel.State,
    modifier: Modifier = Modifier,
) {
    val windowInfo = LocalWindowInfo.current
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = @Composable {
            SignUpTopBar { intent(NicknameViewModel.Intent.OnClickBack) }
        },
        bottomBar = @Composable {
            SignUpBottomBar(
                modifier = Modifier.imePadding(),
                title = stringResource(id = R.string.btn_nickname_input_title),
                enabled = when (state.isValid) {
                    Validation.NicknameValidState.Success -> true
                    else -> false
                },
                onClick = { intent(NicknameViewModel.Intent.OnClickConfirm) },
            )
        },
        containerColor = SGColor.primaryA,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row {
                WidthSpacer(12.dp)
                SGText(
                    text = stringResource(id = R.string.input_nickname_input_title),
                    style = getSGNonScaleTextStyle(
                        color = SGColor.primaryB,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.pretendard,
                    ),
                )
            }

            HeightSpacer(4.dp)

            SGTextFieldTypeForm(
                value = state.nickname,
                textStyle = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
                onValueChange = { intent(NicknameViewModel.Intent.OnNicknameValueChanged(it)) },
                hint = stringResource(id = R.string.input_nickname_input_form_hint_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
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
                        state.isDuplicatedNickname -> stringResource(id = R.string.input_nickname_fail_duplication_hint_text)
                        state.isValid == Validation.NicknameValidState.Regex -> stringResource(id = R.string.input_nickname_fail_regex_hint_text)
                        else -> stringResource(id = R.string.input_nickname_default_hint_text)
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
private fun PreviewNicknameScreen() {
    SGTheme {
        NicknameScreen(
            intent = {},
            state = NicknameViewModel.State(),
        )
    }
}
