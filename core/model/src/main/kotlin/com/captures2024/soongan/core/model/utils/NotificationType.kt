package com.captures2024.soongan.core.model.utils

enum class NotificationType(
    val deepLink: String? = null, // not decide yet. mail, appLink, google form etc..
) {
    CONTEST,
    ACTION,
    ANNOUNCEMENT,
    VINDICATION,
    UNDEFINED,
}
