package com.captures2024.soongan.presentation.feature.root.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.designsystem.ui.component.dialog.SGSingleButtonDialog
import com.captures2024.soongan.presentation.feature.root.R

@Composable
internal fun DialogHostComponent(
    dialogType: CommonDialogType,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit,
) {
    SGSingleButtonDialog(
        content = when (dialogType) {
            CommonDialogType.TOKEN_EXPIRED -> stringResource(R.string.token_expired_dialog_content)
            CommonDialogType.SUCCESS_SIGN -> stringResource(R.string.success_sign_dialog_content)
            CommonDialogType.NETWORK_ERROR -> stringResource(R.string.network_error_dialog_content)
            CommonDialogType.OTHER_SOCIAL -> stringResource(R.string.other_social_dialog_content)
        },
        confirmContent = stringResource(R.string.dialog_confirm),
        onClickConfirm = onClickConfirm,
        onDismissRequest = onClickCancel,
    )
}
