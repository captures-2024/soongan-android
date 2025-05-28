package com.captures2024.soongan.core.model.mock

import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.core.model.utils.NotificationsTable
import java.util.EnumMap

val mockFeedTitleOptions by lazy {
    listOf(1 to "평화", 2 to "즐거움", 3 to "따듯함", 4 to "주제", 5 to "주제", 6 to "주제", 7 to "주제", 8 to "주제", 9 to "주제", 10 to "주제")
}

val mockPosts: List<GalleryPostDto> by lazy {
    listOf(
        GalleryPostDto(
            postId = 0,
            imageUrl = "https://plus.unsplash.com/premium_photo-1673827042837-c9e2076318e9?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 1,
            imageUrl = "https://images.unsplash.com/photo-1527237545644-c3d2a74ede9f?q=80&w=3505&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 2,
            imageUrl = "https://images.unsplash.com/photo-1529797228130-fe918ce6d915?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 3,
            imageUrl = "https://images.unsplash.com/photo-1603072921615-f0d52e2f0c04?q=80&w=3172&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 4,
            imageUrl = "https://plus.unsplash.com/premium_photo-1669688173781-7ac8317079dd?q=80&w=3465&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 5,
            imageUrl = "https://images.unsplash.com/photo-1621496503717-095a410e1566?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 6,
            imageUrl = "https://images.unsplash.com/photo-1590750093844-bc3ae9e48f9c?q=80&w=2404&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 7,
            imageUrl = "https://images.unsplash.com/photo-1484517062256-430351efcbcf?q=80&w=3312&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 8,
            imageUrl = "https://plus.unsplash.com/premium_photo-1676648534602-db1517105311?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 9,
            imageUrl = "https://images.unsplash.com/photo-1612428678564-2573eee0edeb?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 10,
            imageUrl = "https://images.unsplash.com/flagged/photo-1553460646-ea0908947276?q=80&w=3137&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 11,
            imageUrl = "https://images.unsplash.com/photo-1464746133101-a2c3f88e0dd9?q=80&w=3486&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 12,
            imageUrl = "https://plus.unsplash.com/premium_photo-1682432340856-d6cd7cd7090e?q=80&w=3448&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 13,
            imageUrl = "https://images.unsplash.com/photo-1542545319-2807785c15b7?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 14,
            imageUrl = "https://images.unsplash.com/flagged/photo-1595523668648-6e46bf1dfb95?q=80&w=2000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 15,
            imageUrl = "https://images.unsplash.com/photo-1606646677098-46b8141f4da5?q=80&w=3464&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 16,
            imageUrl = "https://plus.unsplash.com/premium_photo-1664353833764-09710438adf9?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 17,
            imageUrl = "https://images.unsplash.com/photo-1523440915059-aa538cbb821a?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 18,
            imageUrl = "https://images.unsplash.com/photo-1543357480-c60d40007a3f?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 19,
            imageUrl = "https://images.unsplash.com/photo-1525451350286-a21d5aef139c?q=80&w=1672&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 20,
            imageUrl = "https://plus.unsplash.com/premium_photo-1678656484471-dcfff7ed95f6?q=80&w=3387&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 21,
            imageUrl = "https://images.unsplash.com/photo-1621367272225-abf78bf7c60a?q=80&w=3434&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 22,
            imageUrl = "https://images.unsplash.com/photo-1554311884-415bfda22b47?q=80&w=2602&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 23,
            imageUrl = "https://images.unsplash.com/photo-1529586691389-2d3d4132856c?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ),
        GalleryPostDto(
            postId = 24,
            imageUrl = "https://plus.unsplash.com/premium_photo-1704908905651-0d14c96a67f0?q=80&w=3540&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
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

    EnumMap<NotificationType, Map<Int, NotificationDto>>(NotificationType::class.java).apply {
        put(NotificationType.CONTEST, tempContestNotifications)
        put(NotificationType.ACTIVITY, tempActivityNotifications)
        put(NotificationType.NOTICE, tempNoticeNotifications)
    }
}

val mockContestNotifications: List<NotificationDto> by lazy {
    listOf(
        NotificationDto(
            id = 1,
            title = "CONTEST_START",
            body = "CONTEST_START",
            subType = NotificationSubType.CONTEST_START,
            createdAt = "1",
        ),
        NotificationDto(
            id = 2,
            title = "CONTEST_END",
            body = "CONTEST_END",
            subType = NotificationSubType.CONTEST_END,
            createdAt = "1",
        ),
    )
}

private val mockActivityNotifications: List<NotificationDto> by lazy {
    listOf(
        NotificationDto(
            id = 3,
            title = "LIKE",
            body = "LIKE",
            subType = NotificationSubType.LIKE,
            createdAt = "1",
        ),
        NotificationDto(
            id = 4,
            title = "COMMENT",
            body = "COMMENT",
            subType = NotificationSubType.COMMENT,
            createdAt = "2",
        ),
        NotificationDto(
            id = 5,
            title = "APPEAL",
            body = "APPEAL",
            subType = NotificationSubType.APPEAL,
            createdAt = "3",
        ),
    )
}

private val mockNoticeNotifications: List<NotificationDto> by lazy {
    listOf(
        NotificationDto(
            id = 6,
            title = "NOTICE",
            body = "NOTICE",
            subType = NotificationSubType.NOTICE,
            createdAt = "1",
        ),
        NotificationDto(
            id = 7,
            title = "NOTICE",
            body = "NOTICE",
            subType = NotificationSubType.NOTICE,
            createdAt = "2",
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
