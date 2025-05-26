package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileViewModel

@Composable
internal fun ProfileRoute(
    navigateToNotification: () -> Unit,
    navigateToPostInfo: (Long) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToFAQ: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileViewModel.Effect.NavigateToEditProfile -> navigateToEditProfile()
                is ProfileViewModel.Effect.NavigateToFAQ -> navigateToFAQ()
                is ProfileViewModel.Effect.NavigateToPostInfo -> navigateToPostInfo(effect.postId)
                is ProfileViewModel.Effect.NavigateToNotification -> navigateToNotification()
                is ProfileViewModel.Effect.NavigateToRegistrationPost -> navigateToRegistrationPost()
            }
        }
    }

    ProfileScreen(
        state = state,
        onClickNotification = { viewModel.intent(ProfileViewModel.Intent.OnClickNotification) },
        onClickMenu = { viewModel.intent(ProfileViewModel.Intent.OnClickMenu) },
        onDismissRequestMenuBottomSheet = { viewModel.intent(ProfileViewModel.Intent.OnDismissRequestMenuBottomSheet) },
        onClickEditProfile = { viewModel.intent(ProfileViewModel.Intent.OnClickEditProfile) },
        onClickFaq = { viewModel.intent(ProfileViewModel.Intent.OnClickFaq) },
        onClickTerms = { viewModel.intent(ProfileViewModel.Intent.OnClickTerms) },
        onRefresh = { viewModel.intent(ProfileViewModel.Intent.OnRefresh) },
        onLoadNextPage = { viewModel.intent(ProfileViewModel.Intent.OnLoadNextPage) },
        onClickPost = { viewModel.intent(ProfileViewModel.Intent.OnClickPost(it)) },
        onClickRegisterPost = { viewModel.intent(ProfileViewModel.Intent.OnClickRegisterPost) },
    )
}
