package com.captures2024.soongan.feature.signUp.state.nickname

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface NicknameSideEffect : UISideEffect {

    data object NavigateToBack : NicknameSideEffect

    data object NavigateToBirthDate : NicknameSideEffect

}