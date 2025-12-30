package com.captures2024.soongan.presentation.feature.main.profile.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.presentation.designsystem.ui.component.dialog.SGDoubleButtonDialog
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.feature.main.profile.component.menu.ProfileMenuItemTopBarComponent
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getDescriptionResId
import com.captures2024.soongan.presentation.feature.main.profile.utils.extension.getTitleResId
import com.captures2024.soongan.presentation.viewmodel.main.profile.ProfileNotificationSettingViewModel
import com.captures2024.soongan.presentation.viewmodel.model.NotificationSettingType

@Composable
internal fun ProfileMenuNotificationSettingScreen(
    state: ProfileNotificationSettingViewModel.State,
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
    onSwitchNotificationSettingType: (NotificationSettingType) -> Unit,
    onClickCancelPermissionDialog: () -> Unit,
    onClickConfirmPermissionDialog: () -> Unit,
) {
    Column(modifier = modifier) {
        ProfileMenuItemTopBarComponent(
            title = stringResource(R.string.profile_menu_notification_setting_title),
            hasBackIcon = true,
            onClickBack = onClickBack,
        )

        NotificationSettingType.entries.forEachIndexed { idx, type ->
            NotificationToggleRow(
                text = stringResource(type.getTitleResId()),
                detailText = stringResource(type.getDescriptionResId()),
                checked = state.notificationSettingState.getStateByType(type),
                onCheckedChange = { onSwitchNotificationSettingType(type) },
            )

            if (idx != NotificationSettingType.entries.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                )
            }
        }
    }

    if (state.isShowPermissionDialog) {
        SGDoubleButtonDialog(
            content = stringResource(R.string.profile_menu_notification_setting_permission_dialog_content),
            confirmContent = stringResource(R.string.profile_menu_notification_setting_permission_dialog_confirm),
            cancelContent = stringResource(R.string.profile_menu_notification_setting_permission_dialog_cancel),
            onClickConfirm = onClickConfirmPermissionDialog,
            onClickCancel = onClickCancelPermissionDialog,
            onDismissRequest = onClickCancelPermissionDialog,
        )
    }
}

@Composable
private fun NotificationToggleRow(
    text: String,
    detailText: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .padding(
                start = 40.dp,
                end = 24.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SGText(
                text = text,
                style = getSGNonScaleTextStyle(
                    color = SGColor.Grayscale.black100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.pretendard,
                    letterSpacing = (-5).em,
                ),
            )

            if (detailText.isNotEmpty()) {
                SGText(
                    text = detailText,
                    style = getSGNonScaleTextStyle(
                        color = SGColor.tempNotificationBody,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 12.sp,
                        fontFamily = SGTypography.pretendard,
                        letterSpacing = 0.em,
                    ),
                )
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(
                height = 28.dp,
                width = 48.dp,
            ),
            colors = SwitchDefaults.colors(
                checkedThumbColor = SGColor.Grayscale.white,
                checkedTrackColor = SGColor.accent,
                checkedBorderColor = SGColor.transparent,
                uncheckedThumbColor = SGColor.Grayscale.white,
                uncheckedTrackColor = SGColor.Grayscale.black100.copy(alpha = 0.3f),
                uncheckedBorderColor = SGColor.transparent,
            ),
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewProfileMenuNotificationSettingScreen() {
    SGTheme {
        ProfileMenuNotificationSettingScreen(
            state = ProfileNotificationSettingViewModel.State(
                isInit = true,
                isCheckedPermission = true,
                notificationSettingState = ProfileNotificationSettingViewModel.State.NotificationSettingState(),
                initNotificationSettingState = null,
                isShowPermissionDialog = false,
            ),
            onClickBack = {},
            onSwitchNotificationSettingType = {},
            onClickCancelPermissionDialog = {},
            onClickConfirmPermissionDialog = {},
        )
    }
}
