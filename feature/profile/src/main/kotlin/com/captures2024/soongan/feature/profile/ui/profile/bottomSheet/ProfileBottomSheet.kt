package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtDepthState
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtOutType
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel.Effect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileBottomSheet(
    closeSheet: (ProfileBtmShtOutType) -> Unit,
    modifier: Modifier = Modifier,
    profileBtmShtViewModel: ProfileBtmShtViewModel = hiltViewModel(),
) {
    val uiState by profileBtmShtViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileBtmShtViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is Effect.OutOfBottomSheet -> closeSheet(sideEffect.outType)
            }
        }
    }

    ProfileBottomSheet(
        uiState = uiState,
        intent = profileBtmShtViewModel::intent,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileBottomSheet(
    uiState: ProfileBtmShtViewModel.State,
    intent: (ProfileBtmShtViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    ModalBottomSheet(
        onDismissRequest = { intent(ProfileBtmShtViewModel.Intent.OnCloseBottomSheet) },
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = SGColor.white
    ) {
        Column {
            when (uiState.depthStatus) {
                ProfileBtmShtDepthState.Idle ->
                    IdleItem(
                        onClickMenuItem = { intent(ProfileBtmShtViewModel.Intent.OnClickMenuItem(it)) }
                    )

                ProfileBtmShtDepthState.Push ->
                    PushItem(
                        pushSetting = uiState.pushSettings,
                        onBackPressed = { intent(ProfileBtmShtViewModel.Intent.OnBackIdle) },
                        onSwitch = { intent(ProfileBtmShtViewModel.Intent.OnPushSettingChanged(it)) }
                    )

                ProfileBtmShtDepthState.Withdraw.Check ->
                    WithDrawCheckItem(
                        onBackPressed = { intent(ProfileBtmShtViewModel.Intent.OnBackIdle) },
                        onClick = { intent(ProfileBtmShtViewModel.Intent.OnCheckProcess(it)) }
                    )

                ProfileBtmShtDepthState.Withdraw.Done ->
                    WithDrawDoneItem(
                        onClick = { intent(ProfileBtmShtViewModel.Intent.OnDoneProcess) }
                    )

                ProfileBtmShtDepthState.SignOut.Check ->
                    SignOutCheckItem(
                        onBackPressed = { intent(ProfileBtmShtViewModel.Intent.OnBackIdle) },
                        onClick = { intent(ProfileBtmShtViewModel.Intent.OnCheckProcess(it)) }
                    )

                ProfileBtmShtDepthState.SignOut.Done ->
                    SignOutDoneItem(
                        onClick = { intent(ProfileBtmShtViewModel.Intent.OnDoneProcess) }
                    )

                ProfileBtmShtDepthState.Error ->
                    ErrorItem(
                        onClick = { intent(ProfileBtmShtViewModel.Intent.OnBackIdle) }
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DevicePreviews
@Composable
private fun ProfileMenuBottomSheetPreview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Expanded,
        density = LocalDensity.current,
        skipHiddenState = false
    )

    ProfileBottomSheet(
        uiState = ProfileBtmShtViewModel.State(),
        intent = {},
        sheetState = sheetState,
    )
}