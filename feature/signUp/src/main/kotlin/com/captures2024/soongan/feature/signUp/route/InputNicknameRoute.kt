package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signUp.NicknameViewModel
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameIntent
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameSideEffect
import com.captures2024.soongan.feature.signUp.ui.InputNicknameScreen
import timber.log.Timber

@Composable
internal fun InputNicknameRoute(
    navigateToBack: () -> Unit,
    navigateToBirthDate: (String) -> Unit,
    nicknameViewModel: NicknameViewModel = hiltViewModel()
) {
    val uiState by nicknameViewModel.state.collectAsStateWithLifecycle()

    Timber.tag("InputNicknameRoute").d("InputNicknameRoute State = $uiState")

    LaunchedEffect(Unit) {
        nicknameViewModel.sideEffect.collect {
            when (it) {
                is NicknameSideEffect.NavigateToBack -> navigateToBack()

                is NicknameSideEffect.NavigateToBirthDate -> navigateToBirthDate(uiState.nickname)

            }
        }
    }

    InputNicknameScreen(
        state = uiState,
        onClickBack = { nicknameViewModel.intent(NicknameIntent.OnClickBack) },
        onChangedNickname = { nicknameViewModel.intent(NicknameIntent.OnValueChanged(it)) },
        onClickConfirm = { nicknameViewModel.intent(NicknameIntent.OnClickConfirm) }
    )
}