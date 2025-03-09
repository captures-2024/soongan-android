package com.captures2024.soongan.core.viewmodel.model.profile

import com.captures2024.soongan.core.common.Validation

data class EditingProfileState(
    val editingProfile: UserProfile = UserProfile(),
    val isEditable: Boolean = false,
    val isDuplicatedNickname: Boolean = false,
) {
    val isValidNickname: Validation.NicknameValidState
        get() = Validation.isValidNickname(editingProfile.nickname)

    val isValidIntroduction: Validation.IntroductionValidState
        get() = Validation.isValidSelfIntroduction(editingProfile.selfIntroduction)
}
