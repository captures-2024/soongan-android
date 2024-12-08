package com.captures2024.soongan.feature.signUp.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.R
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthUIState

@Composable
internal fun InputBirthScreen(
    modifier: Modifier = Modifier,
    state: BirthUIState,
    onClickBack: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onClickConfirm: () -> Unit = {},
) {
    val analyticsHelper = LocalAnalyticsHelper.current

    analyticsHelper.d(
        LogElementArgument("isValid", state.isValid.toString()),
    )

    Scaffold(
        topBar = @Composable {
            SignUpTopBar(onClickBack = onClickBack)
        },
        bottomBar = @Composable {
            SignUpBottomBar(
                modifier = Modifier.imePadding(),
                title = stringResource(id = R.string.input_birth_year_button_title),
                enabled = when (state.isValid) {
                    Validation.BirthYearValidState.Success -> true
                    else -> false
                },
                onClick = onClickConfirm,
            )
        }
    ) { paddingValues ->
        InputBirthBodyScreen(
            modifier = modifier.padding(paddingValues),
            state = state,
            isValid = state.isValid,
            onValueChange = onValueChange
        )
    }
}

@DevicePreviews
@Composable
private fun InputBirthScreenInitPreview() {
    InputBirthScreen(
        state = BirthUIState(nickname = "test")
    )
}

@DevicePreviews
@Composable
private fun InputBirthScreenValueChangedPreview() {
    InputBirthScreen(
        state = BirthUIState(
            nickname = "test",
            birthYear = "2009"
        )
    )
}
