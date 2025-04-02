package com.captures2024.soongan.core.model.utils

import com.captures2024.soongan.core.model.dto.Notification

typealias NotificationsTable = Map<NotificationType, Map<Long, Notification>>

typealias NotificationsCountTable = Map<NotificationType, Int>