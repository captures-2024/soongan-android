package com.captures2024.soongan.feature.home.state.registration_post

import android.net.Uri
import com.captures2024.soongan.core.common.base.UIIntent


internal sealed interface RegistrationPostIntent : UIIntent {

    data object Init : RegistrationPostIntent

    data class InitMedia(
        val mediaUri: Uri?,
    ) : RegistrationPostIntent
}