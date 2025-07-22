package com.captures2024.soongan.presentation.feature.root.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.feature.root.component.DialogHostComponent
import com.captures2024.soongan.presentation.viewmodel.AppViewModel

@Composable
internal fun DialogHost(appViewModel: AppViewModel) {
    val analyticsHelper = LocalAnalyticsHelper.current

    var isShowDialog: Boolean by remember { mutableStateOf(false) }
    var dialog: CommonDialogType? by remember { mutableStateOf(null) }

    LaunchedEffect(appViewModel.sideEffect) {
        appViewModel.sideEffect.collect { effect ->
            when (effect) {
                is AppViewModel.Effect.ShowSingleButtonDialog -> {
                    analyticsHelper.d { "DialogHost::ShowSingleButtonDialog - type: ${effect.type}" }
                    isShowDialog = true
                    dialog = effect.type
                }
                else -> Unit
            }
        }
    }

    val dialogContent = dialog

    if (dialogContent != null && isShowDialog) {
        DialogHostComponent(
            dialogType = dialogContent,
            onClickConfirm = { isShowDialog = false },
            onClickCancel = { isShowDialog = false },
        )
    }
}
