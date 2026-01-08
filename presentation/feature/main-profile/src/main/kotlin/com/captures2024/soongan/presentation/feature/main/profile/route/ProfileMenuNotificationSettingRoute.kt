package com.captures2024.soongan.presentation.feature.main.profile.route

import android.Manifest
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.captures2024.soongan.core.common.permission.openAppSettings
import com.captures2024.soongan.core.common.permission.requestPermissions
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ProfileMenuNotificationSettingScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileNotificationSettingViewModel

@Composable
internal fun ProfileMenuNotificationSettingRoute(
    navigateToBack: () -> Unit,
    viewModel: ProfileNotificationSettingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val permissions = remember {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(Manifest.permission.POST_NOTIFICATIONS)
            else -> emptyArray()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }

        viewModel.intent(ProfileNotificationSettingViewModel.Intent.Permission(areGranted))
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileNotificationSettingViewModel.Effect.NavigateToBack -> navigateToBack()
                is ProfileNotificationSettingViewModel.Effect.CheckPermission -> context.requestPermissions(
                    permissions = permissions,
                    launcher = permissionLauncher,
                )
                is ProfileNotificationSettingViewModel.Effect.OpenAppSetting -> context.openAppSettings()
            }
        }
    }

    LifecycleResumeEffect(
        state.isInit,
        state.isCheckedPermission,
    ) {
        if (state.isInit && !state.isCheckedPermission) {
            viewModel.intent(ProfileNotificationSettingViewModel.Intent.CheckPermission)
        }

        onPauseOrDispose {}
    }

    BackHandler {
        viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnClickBack)
    }

    ProfileMenuNotificationSettingScreen(
        state = state,
        onClickBack = { viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnClickBack) },
        onSwitchNotificationSettingType = { viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnSwitchNotificationSettingType(it)) },
        onClickCancelPermissionDialog = { viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnClickCancelPermissionDialog) },
        onClickConfirmPermissionDialog = { viewModel.intent(ProfileNotificationSettingViewModel.Intent.OnClickConfirmPermissionDialog) },
    )
}
