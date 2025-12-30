package com.captures2024.soongan.core.model.utils

import com.captures2024.soongan.core.model.dto.NotificationDto

typealias NotificationsTable = Map<NotificationType, Map<Int, NotificationDto>>

typealias NotificationsCountTable = Map<NotificationType, Int>
