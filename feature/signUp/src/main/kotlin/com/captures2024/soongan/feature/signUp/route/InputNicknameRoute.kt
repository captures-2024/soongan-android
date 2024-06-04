package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signUp.InputNickNameUIState
import com.captures2024.soongan.feature.signUp.NicknameViewModel
import com.captures2024.soongan.feature.signUp.ui.InputNicknameScreen

@Composable
internal fun InputNicknameRoute(
    navigateToBack: () -> Unit,
    navigateToInputBirthYear: (String, NavOptions) -> Unit,
    nicknameViewModel: NicknameViewModel = hiltViewModel()
) {
    val uiState = nicknameViewModel.uiState.collectAsState()

    when (val value = uiState.value) {
        is InputNickNameUIState.Success -> {
            val options = NavOptions.Builder().build()
            navigateToInputBirthYear(value.nickname, options)
        }
        else -> Unit
    }

    InputNicknameScreen(
        state = uiState.value,
        onChangedNickname = nicknameViewModel::onChangedValue,
        onClickCheckDuplication = nicknameViewModel::duplicationCheck
    )
}