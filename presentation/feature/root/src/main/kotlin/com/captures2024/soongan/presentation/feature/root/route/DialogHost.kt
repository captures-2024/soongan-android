package com.captures2024.soongan.presentation.feature.root.route

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.model.AppConst.External.PLAY_STORE_PREFIX
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.feature.root.R
import com.captures2024.soongan.presentation.feature.root.component.DialogHostComponent
import com.captures2024.soongan.presentation.feature.root.component.dialog.VersionUpdateDialog
import com.captures2024.soongan.presentation.viewmodel.AppViewModel

@Composable
internal fun DialogHost(appViewModel: AppViewModel) {
    val context = LocalContext.current
    val analyticsHelper = LocalAnalyticsHelper.current

    var isShowVersionDialog: Boolean by remember { mutableStateOf(false) }
    var isShowCommonDialog: Boolean by remember { mutableStateOf(false) }
    var dialog: CommonDialogType? by remember { mutableStateOf(null) }

    LaunchedEffect(appViewModel.sideEffect) {
        appViewModel.sideEffect.collect { effect ->
            when (effect) {
                is AppViewModel.Effect.ShowVersionUpdateDialog -> {
                    analyticsHelper.d { "DialogHost::ShowVersionUpdateDialog" }
                    isShowVersionDialog = true
                }

                is AppViewModel.Effect.ShowSingleButtonDialog -> {
                    analyticsHelper.d { "DialogHost::ShowSingleButtonDialog - type: ${effect.type}" }
                    isShowCommonDialog = true
                    dialog = effect.type
                }

                else -> Unit
            }
        }
    }

    val dialogContent = dialog

    if (dialogContent != null && isShowCommonDialog) {
        DialogHostComponent(
            dialogType = dialogContent,
            onClickConfirm = { isShowCommonDialog = false },
            onClickCancel = { isShowCommonDialog = false },
        )
    }

    if (isShowVersionDialog) {
        VersionUpdateDialog(
            content = stringResource(R.string.version_update_dialog_content),
            confirmContent = stringResource(R.string.version_update_dialog_confirm),
            onClickConfirm = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = "$PLAY_STORE_PREFIX${context.packageName}".toUri()
                }
                context.startActivity(intent)
            },
        )
    }
}
