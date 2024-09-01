package com.captures2024.soongan.feature.signUp.state.nickname

import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.UIState

internal data class NicknameUIState(
    val isLoading: Boolean = false,
    val nickname: String = "",
    val isValidNickname: Validation.NicknameValidState = Validation.isValidNickname(nickname),
    val isDuplicatedNickname: Boolean = false,
) : UIState