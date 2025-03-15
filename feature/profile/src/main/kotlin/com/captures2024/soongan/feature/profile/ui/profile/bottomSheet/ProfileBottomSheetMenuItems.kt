package com.captures2024.soongan.feature.profile.ui.profile.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.button.SGTextButtonType2
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingState
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component.ItemText
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component.ItemTopBar
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component.MenuItemTextField
import com.captures2024.soongan.feature.profile.utils.color
import com.captures2024.soongan.feature.profile.utils.detailTextId
import com.captures2024.soongan.feature.profile.utils.icon
import com.captures2024.soongan.feature.profile.utils.textId

@Composable
internal fun IdleItem(
    modifier: Modifier = Modifier,
    onClickMenuItem: (ProfileBtmShtMenuItem) -> Unit = {},
) {
    Column(modifier = modifier) {
        ProfileBtmShtMenuItem.entries.forEachIndexed { idx, item ->
            ProfileMenuRow(
                item = item,
                onClick = onClickMenuItem,
                modifier = Modifier.padding(horizontal = 24.dp),
            )
            if (idx != ProfileBtmShtMenuItem.entries.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = SGColor.primaryA.copy(alpha = 0.3f),
                )
            }
        }
    }
}

@Composable
internal fun PushItem(
    pushSetting: PushSettingState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onSwitch: (PushSettingType) -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(
            itemTitle = stringResource(R.string.bottomSheet_item_title_push_notification_setting),
            hasBackIcon = true,
            onBackPressed = onBackPressed,
        )
        PushSettingType.entries.forEachIndexed { idx, type ->
            PushToggleRow(
                text = type.textId(),
                detailText = type.detailTextId(),
                checked = pushSetting.getStateByType(type),
                onCheckedChange = { onSwitch(type) },
            )
            if (idx != PushSettingType.entries.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = SGColor.primaryA.copy(alpha = 0.3f),
                )
            }
        }
    }
}

@Composable
internal fun WithDrawCheckItem(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onClick: (ProfileBtmShtCheckType) -> Unit = {},
) {
    val input = remember { mutableStateOf("") }

    Column(modifier = modifier) {
        ItemTopBar(
            itemTitle = stringResource(R.string.bottomSheet_item_title_withdraw),
            hasBackIcon = true,
            onBackPressed = onBackPressed,
        )
        ItemContentBox {
            ItemText(stringResource(R.string.bottomSheet_item_withdraw_description_1))
            ItemText("")
            ItemText(stringResource(R.string.bottomSheet_item_withdraw_description_2))
            ItemText(stringResource(R.string.bottomSheet_item_withdraw_description_3))
            HeightSpacer(32.dp)
            MenuItemTextField(
                value = input.value,
                onValueChange = { input.value = it },
                placeholder = stringResource(R.string.bottomSheet_item_title_withdraw),
            )
        }
        ItemButton(
            onClick = { onClick(ProfileBtmShtCheckType.WITHDRAW) },
            text = stringResource(R.string.bottomSheet_item_check_button_text),
            enabled = input.value == stringResource(R.string.bottomSheet_item_title_withdraw),
        )
    }
}

@Composable
internal fun WithDrawDoneItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(itemTitle = stringResource(R.string.bottomSheet_item_title_withdraw))
        ItemContentBox {
            ItemText(stringResource(R.string.bottomSheet_item_withdraw_done_description))
            ItemText(stringResource(R.string.bottomSheet_item_done_description_last))
        }
        HeightSpacer(40.dp)
        ItemButton(
            onClick = onClick,
            text = stringResource(R.string.bottomSheet_item_done_button_text),
        )
    }
}

@Composable
internal fun SignOutCheckItem(
    modifier: Modifier = Modifier,
    onClick: (ProfileBtmShtCheckType) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(
            itemTitle = stringResource(R.string.bottomSheet_item_title_sign_out),
            hasBackIcon = true,
            onBackPressed = onBackPressed,
        )
        ItemContentBox {
            ItemText(stringResource(R.string.bottomSheet_item_sign_out_description))
        }
        ItemButton(
            onClick = { onClick(ProfileBtmShtCheckType.SIGN_OUT) },
            text = stringResource(R.string.bottomSheet_item_title_sign_out),
        )
    }
}

@Composable
internal fun SignOutDoneItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(itemTitle = stringResource(R.string.bottomSheet_item_title_sign_out))
        ItemContentBox {
            ItemText(stringResource(R.string.bottomSheet_item_sign_out_done_description))
            ItemText(stringResource(R.string.bottomSheet_item_done_description_last))
        }
        ItemButton(
            onClick = onClick,
            text = stringResource(R.string.bottomSheet_item_done_button_text),
        )
    }
}

@Composable
internal fun ErrorItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(itemTitle = stringResource(R.string.bottomSheet_item_title_error))
        ItemContentBox {
            ItemText(text = stringResource(R.string.bottomSheet_item_error_description_1))
            ItemText(text = stringResource(R.string.bottomSheet_item_error_description_2))
        }
        ItemButton(onClick = onClick, text = stringResource(R.string.bottomSheet_item_done_button_text))
    }
}

@Composable
private fun ProfileMenuRow(
    item: ProfileBtmShtMenuItem,
    onClick: (ProfileBtmShtMenuItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 18.dp)
            .clickable(
                onClick = { onClick(item) },
                interactionSource = interactionSource,
                indication = null,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SGText(
            text = stringResource(item.textId()),
            style = getSGNonScaleTextStyle(
                color = item.color(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
        )

        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = item.icon(),
                contentDescription = stringResource(item.textId()),
                tint = item.color(),
            )
        }
    }
}

@Composable
private fun PushToggleRow(
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
            .padding(start = 40.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SGText(
                text = text,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
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
                        fontFamily = SGTypography.nanumSquareNeo,
                        letterSpacing = 0.em,
                    ),
                )
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(height = 28.dp, width = 48.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = SGColor.white,
                checkedTrackColor = SGColor.accent,
                checkedBorderColor = SGColor.transparent,
                uncheckedThumbColor = SGColor.white,
                uncheckedTrackColor = SGColor.primaryA.copy(alpha = 0.3f),
                uncheckedBorderColor = SGColor.transparent,
            ),
        )
    }
}

@Composable
private fun ItemContentBox(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp),
    ) {
        content()
    }
}

@Composable
private fun ItemButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    SGTextButtonType2(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 28.dp),
        enabled = enabled,
        onClick = onClick,
    )
}

@DevicePreviews
@Composable
private fun ItemsPreviews() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        IdleItem()
        HeightSpacer(8.dp)

        PushItem(pushSetting = PushSettingState())
        HeightSpacer(8.dp)

        WithDrawCheckItem()
        HeightSpacer(8.dp)

        WithDrawDoneItem()
        HeightSpacer(8.dp)

        SignOutCheckItem()
        HeightSpacer(8.dp)

        SignOutDoneItem()
        HeightSpacer(8.dp)

        ErrorItem()
    }
}
