package com.captures2024.soongan.core.model.mock

import com.captures2024.soongan.core.model.UserPost
import com.captures2024.soongan.core.model.dto.Notification
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.core.model.utils.NotificationsTable
import java.util.EnumMap

val samplePhotos: List<UserPost> by lazy {
    listOf(
        UserPost.PhotoPost(
            id = 0,
            url = "https://plus.unsplash.com/premium_photo-1673827042837-c9e2076318e9?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 1,
            url = "https://images.unsplash.com/photo-1527237545644-c3d2a74ede9f?q=80&w=3505&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 2,
            url = "https://images.unsplash.com/photo-1529797228130-fe918ce6d915?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 3,
            url = "https://images.unsplash.com/photo-1603072921615-f0d52e2f0c04?q=80&w=3172&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 4,
            url = "https://plus.unsplash.com/premium_photo-1669688173781-7ac8317079dd?q=80&w=3465&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 5,
            url = "https://images.unsplash.com/photo-1621496503717-095a410e1566?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 6,
            url = "https://images.unsplash.com/photo-1590750093844-bc3ae9e48f9c?q=80&w=2404&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 7,
            url = "https://images.unsplash.com/photo-1484517062256-430351efcbcf?q=80&w=3312&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 8,
            url = "https://plus.unsplash.com/premium_photo-1676648534602-db1517105311?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 9,
            url = "https://images.unsplash.com/photo-1612428678564-2573eee0edeb?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 10,
            url = "https://images.unsplash.com/flagged/photo-1553460646-ea0908947276?q=80&w=3137&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 11,
            url = "https://images.unsplash.com/photo-1464746133101-a2c3f88e0dd9?q=80&w=3486&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 12,
            url = "https://plus.unsplash.com/premium_photo-1682432340856-d6cd7cd7090e?q=80&w=3448&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 13,
            url = "https://images.unsplash.com/photo-1542545319-2807785c15b7?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 14,
            url = "https://images.unsplash.com/flagged/photo-1595523668648-6e46bf1dfb95?q=80&w=2000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 15,
            url = "https://images.unsplash.com/photo-1606646677098-46b8141f4da5?q=80&w=3464&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 16,
            url = "https://plus.unsplash.com/premium_photo-1664353833764-09710438adf9?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 17,
            url = "https://images.unsplash.com/photo-1523440915059-aa538cbb821a?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 18,
            url = "https://images.unsplash.com/photo-1543357480-c60d40007a3f?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 19,
            url = "https://images.unsplash.com/photo-1525451350286-a21d5aef139c?q=80&w=1672&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 20,
            url = "https://plus.unsplash.com/premium_photo-1678656484471-dcfff7ed95f6?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 21,
            url = "https://images.unsplash.com/photo-1621367272225-abf78bf7c60a?q=80&w=3434&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 22,
            url = "https://images.unsplash.com/photo-1554311884-415bfda22b47?q=80&w=2602&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 23,
            url = "https://images.unsplash.com/photo-1529586691389-2d3d4132856c?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
        UserPost.PhotoPost(
            id = 24,
            url = "https://plus.unsplash.com/premium_photo-1704908905651-0d14c96a67f0?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            title = "",
        ),
    )
}

val mockNotificationsTable: NotificationsTable by lazy {
    val tempContestNotifications = mockContestNotifications.sorted().mapIndexed { index, notification ->
        index to notification
    }.toMap()
    val tempActivityNotifications = mockActivityNotifications.sorted().mapIndexed { index, notification ->
        index to notification
    }.toMap()
    val tempNoticeNotifications = mockNoticeNotifications.sorted().mapIndexed { index, notification ->
        index to notification
    }.toMap()

    EnumMap<NotificationType, Map<Int, Notification>>(NotificationType::class.java).apply {
        put(NotificationType.CONTEST, tempContestNotifications)
        put(NotificationType.ACTIVITY, tempActivityNotifications)
        put(NotificationType.NOTICE, tempNoticeNotifications)
    }
}

val mockContestNotifications: List<Notification> by lazy {
    listOf(
        Notification(
            id = 1,
            title = "CONTEST_START",
            body = "CONTEST_START",
            subType = NotificationSubType.CONTEST_START,
            createdAt = "1"
        ),
        Notification(
            id = 2,
            title = "CONTEST_END",
            body = "CONTEST_END",
            subType = NotificationSubType.CONTEST_END,
            createdAt = "1"
        ),
    )
}

private val mockActivityNotifications: List<Notification> by lazy {
    listOf(
        Notification(
            id = 3,
            title = "LIKE",
            body = "LIKE",
            subType = NotificationSubType.LIKE,
            createdAt = "1"
        ),
        Notification(
            id = 4,
            title = "COMMENT",
            body = "COMMENT",
            subType = NotificationSubType.COMMENT,
            createdAt = "2"
        ),
        Notification(
            id = 5,
            title = "APPEAL",
            body = "APPEAL",
            subType = NotificationSubType.APPEAL,
            createdAt = "3"
        ),
    )
}

private val mockNoticeNotifications: List<Notification> by lazy {
    listOf(
        Notification(
            id = 6,
            title = "NOTICE",
            body = "NOTICE",
            subType = NotificationSubType.NOTICE,
            createdAt = "1"
        ),
        Notification(
            id = 7,
            title = "NOTICE",
            body = "NOTICE",
            subType = NotificationSubType.NOTICE,
            createdAt = "2"
        ),
    )
}

val mockNotificationsCountTable by lazy {
    EnumMap<NotificationType, Int>(NotificationType::class.java).apply {
        put(NotificationType.CONTEST, 2)
        put(NotificationType.ACTIVITY, 3)
        put(NotificationType.NOTICE, 2)
    }
}