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
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateUIState
import timber.log.Timber

@Composable
internal fun InputBirthYearScreen(
    modifier: Modifier = Modifier,
    state: BirthDateUIState,
    onClickBack: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    val isValid = Validation.isValidBirthYear(state.birthDate)
    Timber.tag("InputBirthYearScreen").d("isValid = $isValid")


    Scaffold(
        topBar = @Composable {
            SignUpTopBar(onClickBack = onClickBack)
        },
        bottomBar = @Composable {
            SignUpBottomBar(
                modifier = Modifier.imePadding(),
                title = stringResource(id = R.string.input_birth_year_button_title),
                enabled = when (isValid) {
                    Validation.BirthYearValidState.Success -> true
                    else -> false
                },
                onClick = {}
            )
        }
    ) { paddingValues ->
        InputBirthYearBodyScreen(
            modifier = modifier.padding(paddingValues),
            state = state,
            isValid = isValid,
            onValueChange = onValueChange
        )
    }
}

@DevicePreviews
@Composable
private fun InputBirthYearScreenInitPreview() {
    InputBirthYearScreen(
        state = BirthDateUIState(nickname = "test")
    )
}

@DevicePreviews
@Composable
private fun InputBirthYearScreenValueChangedPreview() {
    InputBirthYearScreen(
        state = BirthDateUIState(nickname = "test")
    )
}