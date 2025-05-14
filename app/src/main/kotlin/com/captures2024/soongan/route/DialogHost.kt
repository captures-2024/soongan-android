package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.designsystem.ui.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.core.viewmodel.AppRootViewModel

@Composable
internal fun DialogHost(viewModel: AppRootViewModel) {
    val analyticsHelper = LocalAnalyticsHelper.current

    var isShowDialog: Boolean by remember { mutableStateOf(false) }
    var dialog: String? by remember { mutableStateOf(null) }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is AppRootViewModel.Effect.ShowSingleButtonDialog -> {
                    analyticsHelper.d { "DialogHost::ShowSingleButtonDialog - content: ${effect.content}" }
                    isShowDialog = true
                    dialog = effect.content
                }
                else -> Unit
            }
        }
    }

    val dialogContent = dialog

    if (dialogContent != null && isShowDialog) {
        SGSingleButtonDialog(
            content = dialogContent,
            confirmContent = "확인",
            onClickConfirm = { isShowDialog = false },
            onDismissRequest = { isShowDialog = false },
        )
    }
}
