package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuSignOutDoneScreen
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuSignOutScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileSignOutViewModel

@Composable
internal fun ProfileMenuSignOutRoute(
    onDismissRequest: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: ProfileSignOutViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileSignOutViewModel.Effect.NavigateToBack -> navigateToBack()
                is ProfileSignOutViewModel.Effect.NavigateToDone -> onDismissRequest()
            }
        }
    }

    when (state.isSuccess) {
        true -> ProfileMenuSignOutDoneScreen(
            onClickDone = { viewModel.intent(ProfileSignOutViewModel.Intent.OnClickDoneSignOut) },
        )

        false -> ProfileMenuSignOutScreen(
            onClickBack = { viewModel.intent(ProfileSignOutViewModel.Intent.OnClickBack) },
            onClickSignOut = { viewModel.intent(ProfileSignOutViewModel.Intent.OnClickConfirmSignOut) },
        )
    }
}
