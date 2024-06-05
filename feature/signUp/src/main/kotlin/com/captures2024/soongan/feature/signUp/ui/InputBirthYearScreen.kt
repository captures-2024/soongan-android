package com.captures2024.soongan.feature.signUp.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.signUp.InputBirthYearUIState
import com.captures2024.soongan.feature.signUp.R

@Composable
internal fun InputBirthYearScreen(
    modifier: Modifier = Modifier,
    state: InputBirthYearUIState,
    onClickBack: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    val isValid = Validation.isValidBirthYear(state.birthYear)
    Log.d("InputBirthYearScreen", "isValid = $isValid")


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
        when (state) {
            is InputBirthYearUIState.Init -> Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = PrimaryA),
            )
            else -> InputBirthYearBodyScreen(
                modifier = modifier.padding(paddingValues),
                state = state,
                isValid = isValid,
                onValueChange = onValueChange
            )
        }
    }
}

@DevicePreviews
@Composable
private fun InputBirthYearScreenInitPreview() {
    InputBirthYearScreen(
        state = InputBirthYearUIState.Init
    )
}

@DevicePreviews
@Composable
private fun InputBirthYearScreenValueChangedPreview() {
    InputBirthYearScreen(
        state = InputBirthYearUIState.ValueChanged(
            nickname = "테스트",
            birthYear = ""
        )
    )
}