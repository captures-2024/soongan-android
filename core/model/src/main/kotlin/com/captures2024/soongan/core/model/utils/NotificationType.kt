package com.captures2024.soongan.core.model.utils

enum class NotificationType(
    val title: String = "",
    val deepLink: String? = null,
) {
    CONTEST("대회 알림"),
    REPORT_EXPLANATION("활동 알림", deepLink = ""), // not decide yet. mail, appLink, google form etc..
    ANNOUNCEMENT("공지 알림"),
    UNDEFINED
}
