package com.captures2024.soongan.core.model.utils

enum class NotificationType {
    CONTEST,
    ACTIVITY,
    NOTICE,
    ;

    companion object {
        fun fromString(value: String): NotificationType? = when (value) {
            CONTEST.toString() -> CONTEST
            ACTIVITY.toString() -> ACTIVITY
            NOTICE.toString() -> NOTICE
            else -> null
        }
    }
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
    ;

    companion object {
        fun fromString(name: String): NotificationSubType = when (name) {
            CONTEST_START.name -> CONTEST_START
            CONTEST_END.name -> CONTEST_END

            COMMENT.name -> COMMENT
            LIKE.name -> LIKE
            APPEAL.name -> APPEAL

            NOTICE.name -> NOTICE

            else -> error("Unknown NotificationSubType: $name")
        }
    }
}
