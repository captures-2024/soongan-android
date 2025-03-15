package com.captures2024.soongan.feature.profile.utils

import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType

fun PushSettingType.textId() = when (this) {
    PushSettingType.ALL -> "전체 알림"
    PushSettingType.CONTEST -> "대회 알림"
    PushSettingType.ACTIVITY -> "활동 알림"
    PushSettingType.NOTICE -> "공지 알림"
}

fun PushSettingType.detailTextId() = when (this) {
    PushSettingType.ALL -> ""
    PushSettingType.CONTEST -> "대회 시작, 최종 투표 시작, 결과 발표 등"
    PushSettingType.ACTIVITY -> "(대)댓글 작성, 신고 접수 및 결과 안내 등"
    PushSettingType.NOTICE -> "공지사항 알림"
}
