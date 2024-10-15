package com.captures2024.soongan.feature.signUp.state.nickname

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface NicknameIntent : UIIntent {

    data object OnClickBack : NicknameIntent

    data class OnValueChanged(
        val nickname: String
    ) : NicknameIntent

    data object OnClickConfirm : NicknameIntent

    data object RegisterNickname : NicknameIntent
}