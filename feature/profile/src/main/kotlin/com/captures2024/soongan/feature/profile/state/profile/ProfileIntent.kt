package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.model.UserPost

internal sealed interface ProfileIntent : UIIntent {

    data class OnClickPhoto(val userPhoto: UserPost.PhotoPost) : ProfileIntent

    data object OnClickNotification : ProfileIntent

    data object OnClickMenu : ProfileIntent

    data object OnClickEdit : ProfileIntent

    data object OnClickNotificationSetting : ProfileIntent

    data object OnClickTermsAndPolicy : ProfileIntent

    data object OnClickFAQ : ProfileIntent

    data object OnClickWithdraw : ProfileIntent

    data object OnClickSignOut : ProfileIntent

    data object OnCloseBottomSheet : ProfileIntent
}