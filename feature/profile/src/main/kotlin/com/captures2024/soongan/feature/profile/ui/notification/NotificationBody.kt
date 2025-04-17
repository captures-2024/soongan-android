package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.ui.component.text.SGText
import com.captures2024.soongan.core.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.core.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.core.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.mock.mockNotificationsTable
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.feature.profile.R
import com.captures2024.soongan.feature.profile.ui.component.SwipeableBox
import com.captures2024.soongan.feature.profile.ui.notification.component.DeleteActionBox
import com.captures2024.soongan.feature.profile.ui.notification.component.NotificationBadge

@Composable
internal fun NotificationBody(
    notifications: Map<Int, NotificationDto>,
    modifier: Modifier = Modifier,
    onClickNotification: (Int) -> Unit = {},
    onDeleteNotification: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        if (notifications.isEmpty()) {
            EmptyNotificationHistory()
        } else {
            NotificationHistory(
                notifications = notifications,
                onClickNotification = onClickNotification,
                onDeleteNotification = onDeleteNotification,
            )
        }
    }
}

@Composable
private fun EmptyNotificationHistory(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        SGText(
            text = stringResource(R.string.non_exist_notification_message),
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 12.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
        )
    }
}

@Composable
private fun NotificationHistory(
    notifications: Map<Int, NotificationDto>,
    modifier: Modifier = Modifier,
    onClickNotification: (Int) -> Unit = {},
    onDeleteNotification: (Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = notifications.entries.toList(),
            key = { it.key },
        ) {
            val notification = it.value
            if (notification.subType == NotificationSubType.APPEAL || notification.subType == NotificationSubType.NOTICE) {
                NotificationHistoryContent(
                    notification = notification,
                    isAppeal = (notification.subType == NotificationSubType.APPEAL),
                    onClick = { onClickNotification(it.key) },
                )
            } else {
                SwipeableBox(
                    actions = {
                        DeleteActionBox(onClick = { onDeleteNotification(it.key) })
                    },
                    content = {
                        NotificationHistoryContent(
                            notification = notification,
                            onClick = { onClickNotification(it.key) },
                        )
                    },
                )
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}

@Composable
private fun NotificationHistoryContent(
    notification: NotificationDto,
    modifier: Modifier = Modifier,
    isAppeal: Boolean = false, // 소명 알림 여부
    onClick: () -> Unit = {},
) {
    // 소명 알림 여부
    val badgeColor =
        if (isAppeal) SGColor.negative
        else {
            if (notification.isRead) SGColor.transparent
            else SGColor.accent
        }
    val titleColor =
        if (isAppeal) SGColor.negative else SGColor.primaryA
    val bodyColor =
        if (isAppeal) SGColor.negative else SGColor.tempNotificationBody

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = !notification.isRead, onClick = onClick),
    ) {
        HeightSpacer(20.dp)
        Row(
            modifier = Modifier.padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NotificationBadge(color = badgeColor)
            WidthSpacer(12.dp)
            SGText(
                text = notification.title,
                style = getSGNonScaleTextStyle(
                    color = titleColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                ),
            )
        }
        HeightSpacer(8.dp)
        SGText(
            text = notification.body,
            style = getSGNonScaleTextStyle(
                color = bodyColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
            modifier = Modifier.padding(horizontal = 32.dp),
        )
        HeightSpacer(8.dp)
        SGText(
            text = notification.createdAt,
            style = getSGNonScaleTextStyle(
                color = bodyColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = (-5).em,
            ),
            modifier = Modifier.padding(horizontal = 32.dp),
        )
        HeightSpacer(20.dp)
    }
}

@DevicePreviews
@Composable
private fun NotificationBodyPreview() {
    NotificationBody(
        notifications = mockNotificationsTable[NotificationType.CONTEST] ?: emptyMap(),
    )
}

@Preview(showBackground = true)
@Composable
private fun NotificationHistoryContentPreview() {
    NotificationHistoryContent(
        notification = NotificationDto(
            title = "알림",
            body = "알림 내용",
            isRead = false,
            createdAt = "2일전",
        ),
    ) {}
}
