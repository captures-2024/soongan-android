package com.captures2024.soongan.presentation.feature.sign_up.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
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
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillCheck
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
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
internal fun InputBirthScreen(
    nickname: String,
    state: SignUpViewModel.State.BirthState,
    modifier: Modifier = Modifier,
    onBrithValueChanged: (String) -> Unit,
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

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = SGColor.white,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(
                    start = 16.dp,
                    end = 14.dp,
                )
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SGText(
                text = nickname,
                style = getSGNonScaleTextStyle(
                    color = SGColor.black80,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = 0.em,
                ),
                modifier = Modifier.weight(1f),
            )

            WidthSpacer(4.dp)

            Icon(
                imageVector = MyIconPack.IconFillCheck,
                contentDescription = stringResource(R.string.nickname_state_icon_description),
                tint = SGColor.accent,
            )
        }

        HeightSpacer(22.dp)

        Row(modifier = Modifier.padding(start = 12.dp)) {
            SGText(
                text = stringResource(R.string.birth_title),
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
            value = state.birthYear,
            onValueChange = onBrithValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            textStyle = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                fontFamily = SGTypography.pretendard,
                letterSpacing = 0.em,
            ),
            hint = stringResource(R.string.birth_hint),
            state = when (state.isValid) {
                Validation.BirthYearValidState.Regex -> SGTextFieldFormState.Error
                Validation.BirthYearValidState.Success -> SGTextFieldFormState.Success
                Validation.BirthYearValidState.Length -> SGTextFieldFormState.Default
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
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
                    Validation.BirthYearValidState.Regex -> stringResource(R.string.birth_error_description_regex)
                    else -> stringResource(R.string.birth_default_description)
                },
                style = getSGNonScaleTextStyle(
                    color = when (state.isValid) {
                        Validation.BirthYearValidState.Regex -> SGColor.negative
                        else -> SGColor.hintGray
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.pretendard,
                ),
            )
        }
    }

    LaunchedEffect(windowInfo) {
        snapshotFlow { windowInfo.isWindowFocused }
            .collect { isWindowFocused ->
                if (isWindowFocused) {
                    focusRequester.requestFocus()
                }
            }
    }
}

@DevicePreviews
@Composable
private fun PreviewInputBirthScreen() {
    SGBackground {
        InputBirthScreen(
            nickname = "test",
            state = SignUpViewModel.State.BirthState(
                birthYear = "2005",
                maxBirthLength = AppConst.Sign.SignUp.MAX_BIRTH_LENGTH,
            ),
            onBrithValueChanged = {},
        )
    }
}
