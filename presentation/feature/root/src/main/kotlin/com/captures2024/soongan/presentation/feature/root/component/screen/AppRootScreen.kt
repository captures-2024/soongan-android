package com.captures2024.soongan.presentation.feature.root.component.screen

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.root.R
import com.captures2024.soongan.presentation.feature.root.component.dialog.GuestModeDialog
import com.captures2024.soongan.presentation.feature.root.component.dialog.VersionUpdateDialog
import com.captures2024.soongan.presentation.viewmodel.AppViewModel
import com.captures2024.soongan.presentation.viewmodel.model.AppRoute
import com.captures2024.soongan.presentation.viewmodel.model.VersionStatus

@Composable
internal fun AppRootScreen(
    intent: (AppViewModel.Intent) -> Unit,
    state: AppViewModel.State,
    appLandingRoute: @Composable () -> Unit,
    appSignRoute: @Composable () -> Unit,
    appMainRoute: @Composable () -> Unit,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (state.rootRouteState) {
            AppRoute.LANDING -> appLandingRoute()

            AppRoute.SIGN -> appSignRoute()

            AppRoute.MAIN -> appMainRoute()
        }

        if (state.appVersionStatus == VersionStatus.NEED_UPDATE) {
            VersionUpdateDialog(
                content = stringResource(R.string.version_update_dialog_content),
                confirmContent = stringResource(R.string.version_update_dialog_confirm),
                onClickConfirm = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(("market://details?id=" + context.packageName).toUri())
                    context.startActivity(intent)
                },
            )
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
                appVersionStatus = VersionStatus.ALREADY_UPDATED,
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
