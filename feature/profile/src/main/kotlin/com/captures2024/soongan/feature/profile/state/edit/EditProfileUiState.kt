package com.captures2024.soongan.feature.profile.state.edit

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class EditProfileUiState(
    val isLoading: Boolean = false,
    val profileImage: String? = null,
    val nickname: String = "user1",
    val selfIntroduction: String = "본인을 소개해주세요",
    val isEditEnabled: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("profileImage", profileImage.toString()),
        LogElementArgument("nickname", nickname),
        LogElementArgument("selfIntroduction", selfIntroduction),
        LogElementArgument("isEditEnabled", isEditEnabled.toString()),
    )

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
//    operator fun compareTo() {
//
//    }
}