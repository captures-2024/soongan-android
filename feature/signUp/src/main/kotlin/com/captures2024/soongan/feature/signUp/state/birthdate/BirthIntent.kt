package com.captures2024.soongan.feature.signUp.state.birthdate

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface BirthIntent : UIIntent {

    data object OnClickBack : BirthIntent

    data class OnValueChanged(
        val birthYear: String
    ) : BirthIntent

    data object OnClickConfirm : BirthIntent
}