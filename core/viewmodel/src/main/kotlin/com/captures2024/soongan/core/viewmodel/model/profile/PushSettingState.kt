package com.captures2024.soongan.core.viewmodel.model.profile

data class PushSettingState(
    val all: Boolean = false,
    val contest: Boolean = false,
    val activity: Boolean = false,
    val notice: Boolean = false,
) {
    fun getStateByType(type: PushSettingType): Boolean {
        return when (type) {
            PushSettingType.ALL -> all
            PushSettingType.CONTEST -> contest
            PushSettingType.ACTIVITY -> activity
            PushSettingType.NOTICE -> notice
        }
    }

    fun switch(type: PushSettingType): PushSettingState {
        return when (type) {
            PushSettingType.ALL -> copy(all = !all)
            PushSettingType.CONTEST -> copy(contest = !contest)
            PushSettingType.ACTIVITY -> copy(activity = !activity)
            PushSettingType.NOTICE -> copy(notice = !notice)
        }
    }
}
