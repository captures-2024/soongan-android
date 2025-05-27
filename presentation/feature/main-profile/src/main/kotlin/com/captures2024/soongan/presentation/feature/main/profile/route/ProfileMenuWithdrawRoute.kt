package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuWithdrawDoneScreen
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuWithdrawScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileWithdrawViewModel

@Composable
internal fun ProfileMenuWithdrawRoute(
    onDismissRequest: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: ProfileWithdrawViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileWithdrawViewModel.Effect.NavigateToBack -> navigateToBack()
                is ProfileWithdrawViewModel.Effect.NavigateToDone -> onDismissRequest()
            }
        }
    }

    when (state.isSuccessWithdraw) {
        true -> ProfileMenuWithdrawDoneScreen(
            onClickDone = { viewModel.intent(ProfileWithdrawViewModel.Intent.OnClickDoneWithdraw) },
        )

        false -> ProfileMenuWithdrawScreen(
            state = state,
            onClickBack = { viewModel.intent(ProfileWithdrawViewModel.Intent.OnClickBack) },
            onInputValueChanged = { viewModel.intent(ProfileWithdrawViewModel.Intent.OnInputValueChanged(it)) },
            onClickWithdraw = { viewModel.intent(ProfileWithdrawViewModel.Intent.OnClickConfirmWithdraw) },
        )
    }
}
