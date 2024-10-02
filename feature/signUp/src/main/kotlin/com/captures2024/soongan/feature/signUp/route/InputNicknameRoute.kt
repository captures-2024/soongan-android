package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.feature.signUp.NicknameViewModel
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameIntent
import com.captures2024.soongan.feature.signUp.state.nickname.NicknameSideEffect
import com.captures2024.soongan.feature.signUp.ui.InputNicknameScreen

@Composable
internal fun InputNicknameRoute(
    navigateToBack: () -> Unit,
    navigateToBirthDate: (String) -> Unit,
    nicknameViewModel: NicknameViewModel = hiltViewModel(),
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val uiState by nicknameViewModel.state.collectAsStateWithLifecycle()

    analyticsHelper.d(
        LogElementArgument("isLoading", uiState.isLoading.toString()),
        LogElementArgument("nickname", uiState.nickname),
        LogElementArgument("isValidNickname", uiState.isValidNickname.toString()),
        LogElementArgument("isDuplicatedNickname", uiState.isDuplicatedNickname.toString()),
    )

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
