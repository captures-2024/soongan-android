package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.feature.main.profile.component.menu.ProfileMenuItemTopBarComponent
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileWithdrawViewModel

@Composable
internal fun ProfileMenuWithdrawScreen(
    state: ProfileWithdrawViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onInputValueChanged: (String) -> Unit,
    onClickWithdraw: () -> Unit,
) {
    val scrollState = rememberScrollState()

    val placeholder = stringResource(R.string.profile_menu_withdraw_placeholder)

    Column(modifier = modifier) {
        ProfileMenuItemTopBarComponent(
            title = stringResource(R.string.profile_menu_withdraw_title),
            hasBackIcon = true,
            onClickBack = onClickBack,
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 40.dp)
                .verticalScroll(scrollState),
        ) {
            SGText(
                text = stringResource(R.string.profile_menu_withdraw_description),
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )

            HeightSpacer(32.dp)

            WithdrawInputTextField(
                value = state.input,
                onValueChange = onInputValueChanged,
                placeholder = placeholder,
            )
        }

        SGTextButtonType2(
            text = stringResource(R.string.button_confirm),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp),
            enabled = state.input == placeholder,
            onClick = onClickWithdraw,
        )
    }
}

@Composable
private fun WithdrawInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
) {
    val focusManager = LocalFocusManager.current

    val contentStyle = getSGNonScaleTextStyle(
        color = SGColor.Grayscale.black100,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontFamily = SGTypography.pretendard,
        letterSpacing = (-5).em,
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = SGColor.Grayscale.black100,
                shape = RoundedCornerShape(8.dp),
            ),
        textStyle = contentStyle,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    SGText(
                        text = placeholder,
                        style = contentStyle.copy(color = SGColor.buttonDisableGray),
                    )
                }
                innerTextField()
            }
        },
    )
}

@DevicePreviews
@Composable
private fun PreviewProfileMenuWithdrawScreen() {
    SGTheme {
        ProfileMenuWithdrawScreen(
            state = ProfileWithdrawViewModel.State(
                input = "",
                isSuccessWithdraw = false,
            ),
            onClickBack = {},
            onInputValueChanged = {},
            onClickWithdraw = {},
        )
    }
}
