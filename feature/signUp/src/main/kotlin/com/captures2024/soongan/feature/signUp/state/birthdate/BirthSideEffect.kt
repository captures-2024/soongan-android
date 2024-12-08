package com.captures2024.soongan.feature.signUp.state.birthdate

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface BirthSideEffect : UISideEffect {

    data object NavigateToBack : BirthSideEffect

    data class NavigateToMain(
        val nickname: String,
        val birthYear: Int,
    ) : BirthSideEffect
}