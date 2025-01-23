package com.captures2024.soongan.feature.signUp.state.birth

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface BirthSideEffect : UISideEffect {

    data object NavigateToBack : BirthSideEffect
}