package com.captures2024.soongan.core.model

import com.captures2024.soongan.core.model.utils.NotificationType
import java.util.UUID

/* 알림 api 구현 시 대체 */
sealed class UserNotification(
    val type: NotificationType = NotificationType.UNDEFINED,
    open val id: Int = UUID.randomUUID().hashCode(),
    open val title: String = "",
    open val body: String = "",
    open val receiveAt: String = "",
    open val isRead: Boolean = false,
) {
    // 대회 알림
    data class Contest(
        override val id: Int,
        override val title: String,
        override val body: String,
        override val receiveAt: String,
        override val isRead: Boolean,
    ) : UserNotification(
        type = NotificationType.CONTEST,
        id = id,
        title = title,
        body = body,
        receiveAt = receiveAt,
        isRead = isRead
    )

    // 활동 알림
    data class Action(
        override val id: Int,
        override val title: String,
        override val body: String,
        override val receiveAt: String,
        override val isRead: Boolean,
    ) : UserNotification(
        type = NotificationType.ACTION,
        id = id,
        title = title,
        body = body,
        receiveAt = receiveAt,
        isRead = isRead
    )

    // 공지 알림
    data class Announcement(
        override val id: Int,
        override val title: String,
        override val body: String,
        override val receiveAt: String,
        override val isRead: Boolean,
    ) : UserNotification(
        type = NotificationType.ANNOUNCEMENT,
        id = id,
        title = title,
        body = body,
        receiveAt = receiveAt,
        isRead = isRead
    )

    // 소명 알림
    data class Vindication(
        override val id: Int,
        override val title: String,
        override val body: String,
        override val receiveAt: String,
        override val isRead: Boolean,
        val completed: Boolean,
    ) : UserNotification(
        type = NotificationType.VINDICATION,
        id = id,
        title = title,
        body = body,
        receiveAt = receiveAt,
        isRead = isRead
    )
}
