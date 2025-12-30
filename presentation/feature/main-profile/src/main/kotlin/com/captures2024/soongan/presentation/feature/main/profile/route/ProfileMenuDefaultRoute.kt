package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuDefaultScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileBottomSheetViewModel

@Composable
internal fun ProfileMenuDefaultRoute(
    navigateToEditProfile: () -> Unit,
    navigateToFaq: () -> Unit,
    navigateToPush: () -> Unit,
    navigateToSignOut: () -> Unit,
    navigateToTerms: () -> Unit,
    navigateToWithdraw: () -> Unit,
    viewModel: ProfileBottomSheetViewModel = hiltViewModel(),
) {
    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                ProfileBottomSheetViewModel.Effect.NavigateToEdit -> navigateToEditProfile()
                ProfileBottomSheetViewModel.Effect.NavigateToFaq -> navigateToFaq()
                ProfileBottomSheetViewModel.Effect.NavigateToPush -> navigateToPush()
                ProfileBottomSheetViewModel.Effect.NavigateToSignOut -> navigateToSignOut()
                ProfileBottomSheetViewModel.Effect.NavigateToTerms -> navigateToTerms()
                ProfileBottomSheetViewModel.Effect.NavigateToWithdraw -> navigateToWithdraw()
            }
        }
    }

    ProfileMenuDefaultScreen(
        onClickMenuItem = { viewModel.intent(ProfileBottomSheetViewModel.Intent.OnClickMenuItem(it)) },
    )
}
