package com.captures2024.soongan.feature.profile.state.notification

internal sealed interface NotificationIntent {

    data object OnBackPressed : NotificationIntent

    data object OnClickNotificationItem : NotificationIntent
}