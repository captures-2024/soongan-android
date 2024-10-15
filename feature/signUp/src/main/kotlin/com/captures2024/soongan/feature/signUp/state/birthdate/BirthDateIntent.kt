package com.captures2024.soongan.feature.signUp.state.birthdate

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface BirthDateIntent : UIIntent {

    data object OnClickBack : BirthDateIntent

    data class OnValueChanged(
        val birthDate: String
    ) : BirthDateIntent

}