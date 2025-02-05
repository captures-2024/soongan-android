package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.field.SGTextFieldFormState
import com.captures2024.soongan.core.designsystem.component.text.field.SGTextFieldTypeForm
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTheme
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.sign.BirthViewModel
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun BirthScreen(
    intent: (BirthViewModel.Intent) -> Unit,
    state: BirthViewModel.State,
    modifier: Modifier = Modifier,
) {
    val windowInfo = LocalWindowInfo.current
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = @Composable {
            SignUpTopBar(onClickBack = { intent(BirthViewModel.Intent.OnClickBack) })
        },
        bottomBar = @Composable {
            SignUpBottomBar(
                modifier = Modifier.imePadding(),
                title = stringResource(id = R.string.input_birth_year_button_title),
                enabled = when (state.isValid) {
                    Validation.BirthYearValidState.Success -> true
                    else -> false
                },
                onClick = { intent(BirthViewModel.Intent.OnClickConfirm) },
            )
        },
        containerColor = SGColor.primaryA,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(40.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                SGText(
                    text = stringResource(id = R.string.input_birth_year_nickname_title),
                    style = SGNonScaleTextStyle(
                        color = SGColor.hintGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        fontFamily = SGTypography.pretendard,
                    )
                )

                HeightSpacer(4.dp)

                SGText(
                    text = state.nickname,
                    style = SGNonScaleTextStyle(
                        color = SGColor.primaryB,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.pretendard,
                    )
                )
            }

            HeightSpacer(36.dp)

            Row {
                WidthSpacer(12.dp)
                SGText(
                    text = stringResource(id = R.string.input_birth_year_title),
                    style = SGNonScaleTextStyle(
                        color = SGColor.primaryB,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp,
                        fontFamily = SGTypography.pretendard,
                    )
                )
            }

            HeightSpacer(4.dp)

            SGTextFieldTypeForm(
                value = state.birthYear,
                textStyle = SGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
                onValueChange = { intent(BirthViewModel.Intent.OnBirthValueChanged(it)) },
                hint = stringResource(id = R.string.input_birth_year_input_form_hint_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                state = when (state.isValid) {
                    Validation.BirthYearValidState.Regex -> SGTextFieldFormState.Error
                    Validation.BirthYearValidState.Success -> SGTextFieldFormState.Success
                    Validation.BirthYearValidState.Length -> SGTextFieldFormState.Default
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
            )

            HeightSpacer(12.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SGText(
                    text = when (state.isValid) {
                        Validation.BirthYearValidState.Regex -> stringResource(id = R.string.input_birth_fail_hint_text)
                        else -> stringResource(id = R.string.input_birth_default_hint_text)
                    },
                    style = SGNonScaleTextStyle(
                        color =  when (state.isValid) {
                            Validation.BirthYearValidState.Regex -> SGColor.negative
                            else -> SGColor.hintGray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        fontFamily = SGTypography.pretendard,
                    )
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
private fun PreviewBirthScreen() {
    SGTheme {
        BirthScreen(
            intent = {},
            state = BirthViewModel.State(
                nickname = "test",
            )
        )
    }
}
