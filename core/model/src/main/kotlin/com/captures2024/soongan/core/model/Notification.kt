package com.captures2024.soongan.core.model

import com.captures2024.soongan.core.model.utils.NotificationSubType

data class Notification(
    val id: Long = -1,
    val title: String = "",
    val body: String = "",
    val subType: NotificationSubType = NotificationSubType.CONTEST_START,
    val isRead: Boolean = false,
    val redirectUrl: String? = null,
    val createdAt: String = "",
) : Comparable<Notification> {

    override fun compareTo(other: Notification): Int {
        // 1. 소명 알림 순
        val thisAppeal = (this.subType == NotificationSubType.APPEAL)
        val otherAppeal = (other.subType == NotificationSubType.APPEAL)

        if (thisAppeal != otherAppeal) {
            return if (thisAppeal) -1 else 1
        }

        // 2. 안 읽음 순
        // 3. 알림 날짜 순
        return compareValuesBy(
            a = this,
            b = other,
            { it.isRead },
            { it.createdAt },
        )
    }
}
