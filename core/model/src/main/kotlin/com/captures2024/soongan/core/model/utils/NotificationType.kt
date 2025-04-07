package com.captures2024.soongan.core.model.utils

enum class NotificationType {
    CONTEST,
    ACTIVITY,
    NOTICE,
}

enum class NotificationSubType(
    val type: NotificationType,
) {
    CONTEST_START(NotificationType.CONTEST),
    CONTEST_END(NotificationType.CONTEST),

    COMMENT(NotificationType.ACTIVITY),
    LIKE(NotificationType.ACTIVITY),
    APPEAL(NotificationType.ACTIVITY),

    NOTICE(NotificationType.NOTICE),
}
