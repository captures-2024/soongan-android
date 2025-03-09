package com.captures2024.soongan.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.designsystem.ui.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.model.AppRootRoute

@Composable
internal fun AppRootScreen(
    intent: (AppRootViewModel.Intent) -> Unit,
    uiState: AppRootViewModel.State,
    appLandingRoute: @Composable () -> Unit,
    appSignRoute: @Composable () -> Unit,
    appMainRoute: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState.rootRouteState) {
            AppRootRoute.LANDING -> appLandingRoute()

            AppRootRoute.SIGN -> appSignRoute()

            AppRootRoute.MAIN -> appMainRoute()
        }

        if (uiState.isShowGuestModeDialog) {
            SGSingleButtonDialog(
                content = "해당 기능은\n로그인 필요한 기능입니다.",
                confirmContent = "확인",
                onClickConfirm = { intent(AppRootViewModel.Intent.OnClickConfirmGuestModeDialog) },
                onDismissRequest = { intent(AppRootViewModel.Intent.OnClickConfirmGuestModeDialog) }
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
            uiState = AppRootViewModel.State(),
            appLandingRoute = {},
            appSignRoute = {},
            appMainRoute = {},
        )
    }
}