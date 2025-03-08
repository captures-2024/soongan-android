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
import com.captures2024.soongan.core.viewmodel.profile.ProfileBottomSheetViewModel
import com.captures2024.soongan.core.viewmodel.profile.ProfileBottomSheetViewModel.Effect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileBottomSheet(
    closeSheet: (ProfileBtmShtOutType) -> Unit,
    modifier: Modifier = Modifier,
    profileBottomSheetViewModel: ProfileBottomSheetViewModel = hiltViewModel(),
) {
    val uiState by profileBottomSheetViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileBottomSheetViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is Effect.OutOfBottomSheet -> closeSheet(sideEffect.outType)
            }
        }
    }

    ProfileBottomSheet(
        uiState = uiState,
        intent = profileBottomSheetViewModel::intent,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileBottomSheet(
    uiState: ProfileBottomSheetViewModel.State,
    intent: (ProfileBottomSheetViewModel.Intent) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    ModalBottomSheet(
        onDismissRequest = { intent(ProfileBottomSheetViewModel.Intent.OnCloseBottomSheet) },
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = SGColor.white
    ) {
        Column {
            when (uiState.depthStatus) {
                ProfileBtmShtDepthState.Idle ->
                    IdleItem(
                        onClickMenuItem = { intent(ProfileBottomSheetViewModel.Intent.OnClickMenuItem(it)) }
                    )

                ProfileBtmShtDepthState.Push ->
                    PushItem(
                        pushSetting = uiState.pushSettings,
                        onBackPressed = { intent(ProfileBottomSheetViewModel.Intent.OnBackIdle) },
                        onSwitch = { intent(ProfileBottomSheetViewModel.Intent.OnPushSettingChanged(it)) }
                    )

                ProfileBtmShtDepthState.Withdraw.Check ->
                    WithDrawCheckItem(
                        onBackPressed = { intent(ProfileBottomSheetViewModel.Intent.OnBackIdle) },
                        onClick = { intent(ProfileBottomSheetViewModel.Intent.OnCheckProcess(it)) }
                    )

                ProfileBtmShtDepthState.Withdraw.Done ->
                    WithDrawDoneItem(
                        onClick = { intent(ProfileBottomSheetViewModel.Intent.OnDoneProcess) }
                    )

                ProfileBtmShtDepthState.SignOut.Check ->
                    SignOutCheckItem(
                        onBackPressed = { intent(ProfileBottomSheetViewModel.Intent.OnBackIdle) },
                        onClick = { intent(ProfileBottomSheetViewModel.Intent.OnCheckProcess(it)) }
                    )

                ProfileBtmShtDepthState.SignOut.Done ->
                    SignOutDoneItem(
                        onClick = { intent(ProfileBottomSheetViewModel.Intent.OnDoneProcess) }
                    )

                ProfileBtmShtDepthState.Error ->
                    ErrorItem(
                        onClick = { intent(ProfileBottomSheetViewModel.Intent.OnBackIdle) }
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
        uiState = ProfileBottomSheetViewModel.State(),
        intent = {},
        sheetState = sheetState,
    )
}