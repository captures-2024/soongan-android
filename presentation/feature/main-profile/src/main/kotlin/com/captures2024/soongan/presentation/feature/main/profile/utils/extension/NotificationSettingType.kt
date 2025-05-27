package com.captures2024.soongan.presentation.feature.main.profile.utils.extension

import androidx.annotation.StringRes
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.model.NotificationSettingType

@StringRes
fun NotificationSettingType.getTitleResId(): Int = when (this) {
    NotificationSettingType.ALL -> R.string.notification_setting_type_title_all
    NotificationSettingType.CONTEST -> R.string.notification_setting_type_title_contest
    NotificationSettingType.ACTIVITY -> R.string.notification_setting_type_title_activity
    NotificationSettingType.NOTICE -> R.string.notification_setting_type_title_notice
}

@StringRes
fun NotificationSettingType.getDescriptionResId(): Int = when (this) {
    NotificationSettingType.ALL -> R.string.notification_setting_type_description_all
    NotificationSettingType.CONTEST -> R.string.notification_setting_type_description_contest
    NotificationSettingType.ACTIVITY -> R.string.notification_setting_type_description_activity
    NotificationSettingType.NOTICE -> R.string.notification_setting_type_description_notice
}
