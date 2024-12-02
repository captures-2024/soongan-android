package com.captures2024.soongan.feature.profile.state.edit

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface EditProfileIntent : UIIntent {

    data object OnBackPressed : EditProfileIntent

    data object OnClickProfileImage : EditProfileIntent

    data class OnProfileImageChanged(
        val newProfileImage: String,
    ) : EditProfileIntent

    data class OnNicknameChanged(
        val newNickname: String,
    ) : EditProfileIntent

    data class OnIntroductionChanged(
        val newIntroduction: String,
    ) : EditProfileIntent

    data object OnClickEditButton : EditProfileIntent
}