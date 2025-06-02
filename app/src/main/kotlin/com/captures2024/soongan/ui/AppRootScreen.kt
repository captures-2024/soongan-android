package com.captures2024.soongan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.designsystem.ui.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
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
            SGSingleButtonDialog(
                content = "해당 기능은\n로그인 필요한 기능입니다.",
                confirmContent = "확인",
                onClickConfirm = { intent(AppViewModel.Intent.OnClickConfirmGuestModeDialog) },
                onDismissRequest = { intent(AppViewModel.Intent.OnClickConfirmGuestModeDialog) },
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
