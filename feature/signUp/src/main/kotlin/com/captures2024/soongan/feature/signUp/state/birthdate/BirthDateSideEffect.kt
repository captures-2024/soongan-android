package com.captures2024.soongan.feature.signUp.state.birthdate

import com.captures2024.soongan.core.common.base.UISideEffect

internal sealed interface BirthDateSideEffect : UISideEffect {

    data object NavigateToBack : BirthDateSideEffect

    data object NavigateToMain : BirthDateSideEffect

}