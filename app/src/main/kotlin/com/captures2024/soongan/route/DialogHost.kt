package com.captures2024.soongan.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.designsystem.ui.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.viewmodel.AppViewModel

@Composable
internal fun DialogHost(viewModel: AppViewModel) {
    val analyticsHelper = LocalAnalyticsHelper.current

    var isShowDialog: Boolean by remember { mutableStateOf(false) }
    var dialog: CommonDialogType? by remember { mutableStateOf(null) }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
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
        SGSingleButtonDialog(
            content = when (dialogContent) {
                CommonDialogType.TOKEN_EXPIRED -> "토큰이 만료되었습니다. 다시 로그인해주세요."
                CommonDialogType.SUCCESS_SIGN -> "로그인이 완료되었습니다."
                CommonDialogType.NETWORK_ERROR -> "네트워크 오류가 발생하였습니다."
            },
            confirmContent = "확인",
            onClickConfirm = { isShowDialog = false },
            onDismissRequest = { isShowDialog = false },
        )
    }
}
