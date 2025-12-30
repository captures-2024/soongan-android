package com.captures2024.soongan.presentation.feature.main.profile.utils.extension

import androidx.annotation.StringRes
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.presentation.feature.main.profile.R

@StringRes
internal fun NotificationType.getTabResId(): Int = when (this) {
    NotificationType.CONTEST -> R.string.notification_tab_contest
    NotificationType.ACTIVITY -> R.string.notification_tab_activity
    NotificationType.NOTICE -> R.string.notification_tab_notice
}
