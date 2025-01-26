package com.captures2024.soongan.feature.profile.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.theme.NanumSquareNeoFontFamily
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtDepthStatus
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType
import com.captures2024.soongan.core.viewmodel.profile.ProfileBtmShtViewModel
import com.captures2024.soongan.feature.profile.utils.color
import com.captures2024.soongan.feature.profile.utils.icon
import com.captures2024.soongan.feature.profile.utils.textId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileMenuBottomSheet(
    uiState: ProfileBtmShtViewModel.State,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    closeSheet: () -> Unit = {},
    onClickMenuItem: (ProfileBtmShtMenuItem) -> Unit = {},
    onBackIdle: () -> Unit = {},
    onCheckProcess: (type: ProfileBtmShtCheckType) -> Unit = {},
    onDoneProcess: () -> Unit = {},
    onPushSettingChanged: (PushSettingType) -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = closeSheet,
        modifier = modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            when (uiState.depthStatus) {
                ProfileBtmShtDepthStatus.Idle -> {
                    ProfileBtmShtMenuItem.entries.forEachIndexed { idx, item ->
                        ProfileMenuRow(
                            item = item,
                            onClick = onClickMenuItem
                        )
                        if (idx != ProfileBtmShtMenuItem.entries.lastIndex) {
                            HorizontalDivider(color = SGColor.primaryA.copy(alpha = 0.3f))
                        }
                    }
                }

                ProfileBtmShtDepthStatus.Push -> NonScaleText("push 알림 설정", 24.sp)

                ProfileBtmShtDepthStatus.Withdraw.Check -> NonScaleText("회원 탈퇴 ?", 24.sp)

                ProfileBtmShtDepthStatus.Withdraw.Done -> NonScaleText("회원 탈퇴 o", 24.sp)

                ProfileBtmShtDepthStatus.SignOut.Check -> NonScaleText("로그 아웃 ?", 24.sp)

                ProfileBtmShtDepthStatus.SignOut.Done -> NonScaleText("로그 아웃 o", 24.sp)

                ProfileBtmShtDepthStatus.Error -> NonScaleText("다시 시도해 주세요.", 24.sp)
            }
        }
    }
}

@Composable
private fun ProfileMenuRow(
    modifier: Modifier = Modifier,
    item: ProfileBtmShtMenuItem,
    onClick: (ProfileBtmShtMenuItem) -> Unit = {},
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

    ProfileMenuBottomSheet(uiState = ProfileBtmShtViewModel.State(), sheetState = sheetState)
}