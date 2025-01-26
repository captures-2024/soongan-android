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
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.component.SoonGanButton
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingState
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component.ItemText
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component.ItemTopBar
import com.captures2024.soongan.feature.profile.ui.profile.bottomSheet.component.MenuItemTextField
import com.captures2024.soongan.feature.profile.utils.color
import com.captures2024.soongan.feature.profile.utils.detailTextId
import com.captures2024.soongan.feature.profile.utils.icon
import com.captures2024.soongan.feature.profile.utils.textId

private const val WITHDRAW_CHECK_KEY = "회원탈퇴"

@Composable
internal fun IdleItem(
    modifier: Modifier = Modifier,
    onClickMenuItem: (ProfileBtmShtMenuItem) -> Unit = {},
) {
    Column(modifier = modifier.padding()) {
        ProfileBtmShtMenuItem.entries.forEachIndexed { idx, item ->
            ProfileMenuRow(
                item = item,
                onClick = onClickMenuItem,
                modifier = Modifier.padding(horizontal = 24.dp),
            )
            if (idx != ProfileBtmShtMenuItem.entries.lastIndex) {
                HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))
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
            itemTitle = "푸시 알림 설정",
            hasBackIcon = true,
            onBackPressed = onBackPressed
        )
        PushSettingType.entries.forEachIndexed { idx, type ->
            PushToggleRow(
                text = type.textId(),
                detailText = type.detailTextId(),
                checked = pushSetting.getStateByType(type),
                onCheckedChange = { onSwitch(type) }
            )
            if (idx != PushSettingType.entries.lastIndex) {
                HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))
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
            itemTitle = "회원탈퇴",
            hasBackIcon = true,
            onBackPressed = onBackPressed
        )
        ItemContentBox {
            ItemText("정말 회원탈퇴를 하실 건가요?")
            ItemText("")
            ItemText("회원탈퇴를 위해 아래 입력창에")
            ItemText("'회원탈퇴'를 입력해주세요")
            HeightSpacer(32.dp)
            MenuItemTextField(
                value = input.value,
                onValueChange = { input.value = it },
                placeholder = "회원탈퇴"
            )
        }
        ItemButton(
            onClick = { onClick(ProfileBtmShtCheckType.WITHDRAW) },
            text = "완료",
            enabled = input.value == WITHDRAW_CHECK_KEY
        )
    }
}

@Composable
internal fun WithDrawDoneItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(itemTitle = "회원탈퇴")
        ItemContentBox {
            ItemText("회원탈퇴가 완료되었습니다.")
            ItemText("또 만나길 바랄게요!")
        }
        HeightSpacer(40.dp)
        ItemButton(
            onClick = onClick,
            text = "확인"
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
            itemTitle = "로그아웃",
            hasBackIcon = true,
            onBackPressed = onBackPressed
        )
        ItemContentBox {
            ItemText("정말 로그아웃 하실 건가요?")
        }
        ItemButton(
            onClick = { onClick(ProfileBtmShtCheckType.SIGN_OUT) },
            text = "로그아웃"
        )
    }
}

@Composable
internal fun SignOutDoneItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(itemTitle = "로그아웃")
        ItemContentBox {
            ItemText("로그아웃이 완료됐습니다.")
            ItemText("또 만나길 바랄게요!")
        }
        ItemButton(
            onClick = onClick,
            text = "확인"
        )
    }
}

@Composable
internal fun ErrorItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        ItemTopBar(itemTitle = "에러")
        ItemContentBox {
            ItemText(text = "해당 요청이 처리되지 않았습니다.")
            ItemText(text = "다시 시도해주세요.")
        }
        ItemButton(onClick = onClick, text = "확인")
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
            .padding(horizontal = 16.dp)
            .clickable(
                onClick = { onClick(item) },
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NonScaleText(
            text = stringResource(item.textId()),
            fontSize = 16.sp,
            color = item.color(),
            fontWeight = FontWeight.Bold,
            fontFamily = NanumSquareNeoFontFamily,
            letterSpacing = 0.sp,
            lineHeight = 24.sp
        )
        Box(
            modifier = Modifier.size(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon(),
                contentDescription = stringResource(item.textId()),
                tint = item.color()
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
            .height(80.dp)
            .padding(start = 40.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            NonScaleText(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NanumSquareNeoFontFamily,
                letterSpacing = (-0.05).em,
                lineHeight = 20.sp
            )
            NonScaleText(
                text = detailText,
                fontSize = 12.sp,
                color = SGColor.primaryA.copy(alpha = 0.6f),
                fontWeight = FontWeight.Normal,
                fontFamily = NanumSquareNeoFontFamily,
                letterSpacing = 0.sp,
                lineHeight = 12.sp
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = SGColor.white,
                checkedTrackColor = SGColor.accent,
                checkedBorderColor = SGColor.transparent,
                uncheckedThumbColor = SGColor.white,
                uncheckedTrackColor = SGColor.primaryA.copy(alpha = 0.3f),
                uncheckedBorderColor = SGColor.transparent
            )
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
    SoonGanButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 28.dp),
        enabled = enabled
    ) {
        ItemText(text = text)
    }
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