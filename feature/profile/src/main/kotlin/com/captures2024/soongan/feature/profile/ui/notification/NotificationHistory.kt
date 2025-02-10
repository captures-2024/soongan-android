package com.captures2024.soongan.feature.profile.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.HeightSpacer
import com.captures2024.soongan.core.designsystem.component.WidthSpacer
import com.captures2024.soongan.core.designsystem.component.text.SGText
import com.captures2024.soongan.core.designsystem.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.designsystem.theme.SGTypography
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.core.model.UserNotification
import com.captures2024.soongan.core.model.mock.mockNotifications
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.feature.profile.R

@Composable
internal fun NotificationHistory(
    modifier: Modifier = Modifier,
    notifications: List<UserNotification>,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (notifications.isEmpty()) {
            EmptyNotificationHistory()
        } else {
            LazyColumn {
                items(notifications, key = { it.id }) {
                    NotificationHistoryContent(
                        title = it.title,
                        body = it.body,
                        receiveAt = it.receiveAt,
                        isRead = it.isRead,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyNotificationHistory(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SGText(
            text = stringResource(R.string.non_exist_notification_message),
            style = getSGNonScaleTextStyle(
                color = SGColor.primaryA,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp,
                fontFamily = SGTypography.nanumSquareNeo,
                letterSpacing = 0.em,
            )
        )
    }
}

@Composable
private fun NotificationHistoryContent(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    receiveAt: String,
    isRead: Boolean = false,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        HeightSpacer(20.dp)

        Row(
            modifier = Modifier.padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = if (isRead) SGColor.transparent else SGColor.accent)
            )

            WidthSpacer(12.dp)

            SGText(
                text = title,
                style = getSGNonScaleTextStyle(
                    color = SGColor.primaryA,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp,
                    fontFamily = SGTypography.nanumSquareNeo,
                    letterSpacing = (-5).em,
                )
            )
        }

        HeightSpacer(8.dp)

        SGText(
            text = body,
            style = getSGNonScaleTextStyle(
                color = SGColor.tempNotificationBody,
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
            text = receiveAt,
            style = getSGNonScaleTextStyle(
                color = SGColor.tempNotificationBody,
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

    HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
}


@DevicePreviews
@Composable
private fun NotificationHistoryPreview() {
    val mockNotifications =
        mockNotifications.filter { it.type == NotificationType.ACTION || it.type == NotificationType.VINDICATION }

    NotificationHistory(notifications = mockNotifications)
}