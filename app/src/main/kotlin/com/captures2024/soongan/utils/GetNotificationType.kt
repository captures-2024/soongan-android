package com.captures2024.soongan.utils

import com.captures2024.soongan.core.model.utils.NotificationType

internal fun getNotificationType(type: String?): NotificationType =
    NotificationType.entries.find { it.name == type } ?: NotificationType.UNDEFINED
