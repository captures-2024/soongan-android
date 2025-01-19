package com.captures2024.soongan.feature.profile.state.profile

import com.captures2024.soongan.core.common.base.UIIntent

internal sealed interface ProfileIntent : UIIntent {

    // Profile Screen - Intent
    sealed interface ProfileI : ProfileIntent {

        data object Init : ProfileI

        data object RefreshMyGallery : ProfileI

        data object LoadNextPage : ProfileI

        data class OnClickPhoto(val postId: Int) : ProfileI

        data object OnClickRegistrationText : ProfileI

        data object OnClickNotification : ProfileI

        data object OnClickMenu : ProfileI
    }

    // Profile Menu Bottom Sheet - Intent
    sealed interface BottomSheetI : ProfileIntent {

        data object OnClickEdit : BottomSheetI

        data object OnClickNotificationSetting : BottomSheetI

        data object OnClickTermsAndPolicy : BottomSheetI

        data object OnClickFAQ : BottomSheetI

        data object OnClickWithdraw : BottomSheetI

        data object OnClickSignOut : BottomSheetI

        data object OnCloseBottomSheet : BottomSheetI
    }

    // Edit Screen - Intent
    sealed interface EditI : ProfileIntent {

        data object OnBackPressed : EditI

        data object OnClickProfileImage : EditI

        data object OnClickDefaultProfileImage : EditI

        data object OpenPhotoPicker : EditI

        data object OnCloseEditBottomSheet : EditI

        data class OnProfileImageChanged(
            val newProfileImage: String,
        ) : EditI

        data class OnNicknameChanged(
            val newNickname: String,
        ) : EditI

        data class OnIntroductionChanged(
            val newIntroduction: String,
        ) : EditI

        data object OnClickEditButton : EditI
    }
}