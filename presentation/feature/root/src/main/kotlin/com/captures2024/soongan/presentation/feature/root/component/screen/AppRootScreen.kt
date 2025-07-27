package com.captures2024.soongan.presentation.feature.root.component.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.root.R
import com.captures2024.soongan.presentation.feature.root.component.dialog.GuestModeDialog
import com.captures2024.soongan.presentation.viewmodel.AppViewModel
import com.captures2024.soongan.presentation.viewmodel.model.AppRoute

@Composable
internal fun AppRootScreen(
    intent: (AppViewModel.Intent) -> Unit,
    state: AppViewModel.State,
    appLandingRoute: @Composable () -> Unit,
    appSignRoute: @Composable () -> Unit,
    appMainRoute: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (state.rootRouteState) {
            AppRoute.LANDING -> appLandingRoute()

            AppRoute.SIGN -> appSignRoute()

            AppRoute.MAIN -> appMainRoute()
        }

        if (state.isShowGuestModeDialog) {
            GuestModeDialog(
                content = stringResource(R.string.guest_mode_dialog_content),
                confirmContent = stringResource(R.string.dialog_confirm),
                onClickConfirm = { intent(AppViewModel.Intent.OnClickConfirmGuestModeDialog) },
                onDismissRequest = { intent(AppViewModel.Intent.OnClickDismissGuestModeDialog) },
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PreviewAppRootScreen() {
    SGTheme {
        AppRootScreen(
            intent = {},
            state = AppViewModel.State(
                isInitialized = false,
                isGuestMode = false,
                isLoading = false to System.currentTimeMillis(),
                isShowGuestModeDialog = false,
                currentMember = null,
            ),
            appLandingRoute = {},
            appSignRoute = {},
            appMainRoute = {},
        )
    }
}
