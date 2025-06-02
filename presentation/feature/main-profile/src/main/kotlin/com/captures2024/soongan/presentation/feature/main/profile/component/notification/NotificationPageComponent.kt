package com.captures2024.soongan.presentation.feature.main.profile.component.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.captures2024.soongan.presentation.designsystem.icon.MyIconPack
import com.captures2024.soongan.presentation.designsystem.icon.myiconpack.IconNonFillDelete
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.mock.mockNotificationsTable
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.presentation.feature.main.profile.R

@Composable
internal fun NotificationPageComponent(
    notifications: Map<Int, NotificationDto>,
    modifier: Modifier = Modifier,
    onClickNotification: (Int) -> Unit,
    onDeleteNotification: (Int) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        when (notifications.isEmpty()) {
            true -> NotificationEmptyComponent()

            false -> LazyColumn(modifier = modifier) {
                items(
                    items = notifications.entries.toList(),
                    key = { it.key },
                ) {
                    val notification = it.value

                    when (notification.subType) {
                        NotificationSubType.APPEAL,
                        NotificationSubType.NOTICE,
                        -> NotificationItemComponent(
                            notification = notification,
                            isAppeal = (notification.subType == NotificationSubType.APPEAL),
                            onClick = { onClickNotification(it.key) },
                        )

                        else -> NotificationSwipeableBoxComponent(
                            actions = @Composable {
                                DeleteActionBox(
                                    onClick = { onDeleteNotification(it.key) },
                                )
                            },
                            onExpanded = {},
                            onCollapsed = {},
                            content = @Composable {
                                NotificationItemComponent(
                                    notification = notification,
                                    onClick = { onClickNotification(it.key) },
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DeleteActionBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .fillMaxHeight()
            .background(SGColor.negative)
            .padding(horizontal = 12.dp)
            .clickable(onClick = onClick),
    ) {
        Icon(
            imageVector = MyIconPack.IconNonFillDelete,
            contentDescription = stringResource(R.string.delete_description),
            modifier = Modifier.align(Alignment.Center),
            tint = SGColor.Grayscale.white,
        )
    }
}

@DevicePreviews
@Composable
private fun PreviewNotificationPageComponent() {
    SGTheme {
        NotificationPageComponent(
            notifications = mockNotificationsTable[NotificationType.CONTEST] ?: emptyMap(),
            onClickNotification = {},
            onDeleteNotification = {},
        )
    }
}
