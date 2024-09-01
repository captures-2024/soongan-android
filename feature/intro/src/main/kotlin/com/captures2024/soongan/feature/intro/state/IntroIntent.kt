package com.captures2024.soongan.feature.intro.state

import com.captures2024.soongan.core.common.base.UIIntent

sealed interface IntroIntent : UIIntent {

    data object RefreshUserData : IntroIntent

}