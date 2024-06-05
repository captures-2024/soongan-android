package com.captures2024.soongan.feature.signUp.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signUp.InputNickNameUIState
import com.captures2024.soongan.feature.signUp.NicknameViewModel
import com.captures2024.soongan.feature.signUp.navigation.INPUT_NICKNAME_NAVIGATION_ROUTE
import com.captures2024.soongan.feature.signUp.ui.InputNicknameScreen

@Composable
internal fun InputNicknameRoute(
    navigateToBack: () -> Unit,
    navigateToInputBirthYear: (String, NavOptions) -> Unit,
    nicknameViewModel: NicknameViewModel = hiltViewModel()
) {
    val uiState = nicknameViewModel.uiState.collectAsStateWithLifecycle()

    Log.d("InputNicknameRoute", "InputNicknameRoute State = ${uiState.value}")

    when (val value = uiState.value) {
        is InputNickNameUIState.Success -> {
            nicknameViewModel.restoreState()
            val options = NavOptions.Builder().build()
            navigateToInputBirthYear(value.nickname, options)
        }
        else -> Unit
    }

    InputNicknameScreen(
        state = uiState.value,
        onClickBack = navigateToBack,
        onChangedNickname = nicknameViewModel::onChangedValue,
        onClickCheckDuplication = nicknameViewModel::duplicationCheck
    )
}