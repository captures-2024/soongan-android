package com.captures2024.soongan.presentation.feature.main.profile.component.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.presentation.designsystem.ui.component.HeightSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.WidthSpacer
import com.captures2024.soongan.presentation.designsystem.ui.component.text.SGText
import com.captures2024.soongan.presentation.designsystem.ui.component.text.getSGNonScaleTextStyle
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTypography
import com.captures2024.soongan.presentation.designsystem.ui.util.DevicePreviews

@Composable
internal fun NotificationItemComponent(
    notification: NotificationDto,
    modifier: Modifier = Modifier,
    isAppeal: Boolean = false, // 소명 알림 여부
    onClick: () -> Unit,
) {
    // 소명 알림 여부
    val badgeColor = when (isAppeal) {
        true -> SGColor.negative

        false -> when (notification.isRead) {
            true -> SGColor.transparent
            false -> SGColor.accent
        }
    }

    val titleColor = when (isAppeal) {
        true -> SGColor.negative
        false -> SGColor.Grayscale.black100
    }

    val bodyColor = when (isAppeal) {
        true -> SGColor.negative
        false -> SGColor.tempNotificationBody
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = !notification.isRead,
                onClick = onClick,
            ),
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
                    fontFamily = SGTypography.pretendard,
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
                fontFamily = SGTypography.pretendard,
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
                fontFamily = SGTypography.pretendard,
                letterSpacing = (-5).em,
            ),
            modifier = Modifier.padding(horizontal = 32.dp),
        )

        HeightSpacer(20.dp)
    }
}

@Composable
private fun NotificationBadge(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color = color),
    )
}

@DevicePreviews
@Composable
private fun PreviewNotificationItemComponent() {
    SGTheme {
        NotificationItemComponent(
            notification = NotificationDto(
                title = "알림",
                body = "알림 내용",
                isRead = false,
                createdAt = "2일전",
            ),
            onClick = {},
        )
    }
}
