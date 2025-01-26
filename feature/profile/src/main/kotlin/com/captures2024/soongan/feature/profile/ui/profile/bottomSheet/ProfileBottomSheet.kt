package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtDepthState
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileBottomSheet(
    uiState: ProfileBtmShtViewModel.State,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    closeSheet: () -> Unit = {},
    onClickMenuItem: (ProfileBtmShtMenuItem) -> Unit = {},
    onBackIdle: () -> Unit = {},
    onCheckProcess: (ProfileBtmShtCheckType) -> Unit = {},
    onDoneProcess: () -> Unit = {},
    onPushSettingChanged: (PushSettingType) -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = SGColor.white
    ) {
        Column {
            when (uiState.depthStatus) {
                ProfileBtmShtDepthState.Idle ->
                    IdleItem(onClickMenuItem = onClickMenuItem)

                ProfileBtmShtDepthState.Push ->
                    PushItem(
                        pushSetting = uiState.pushSettings,
                        onBackPressed = onBackIdle,
                        onSwitch = onPushSettingChanged
                    )

                ProfileBtmShtDepthState.Withdraw.Check ->
                    WithDrawCheckItem(
                        onBackPressed = onBackIdle,
                        onClick = onCheckProcess
                    )

                ProfileBtmShtDepthState.Withdraw.Done ->
                    WithDrawDoneItem(onClick = onDoneProcess)

                ProfileBtmShtDepthState.SignOut.Check ->
                    SignOutCheckItem(
                        onBackPressed = onBackIdle,
                        onClick = onCheckProcess
                    )

                ProfileBtmShtDepthState.SignOut.Done ->
                    SignOutDoneItem(onClick = onDoneProcess)

                ProfileBtmShtDepthState.Error ->
                    ErrorItem(onClick = onBackIdle)
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

    ProfileBottomSheet(uiState = ProfileBtmShtViewModel.State(), sheetState = sheetState)
}