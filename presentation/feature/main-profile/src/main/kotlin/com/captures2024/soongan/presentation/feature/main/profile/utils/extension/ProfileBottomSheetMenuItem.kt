package com.captures2024.soongan.presentation.feature.main.profile.utils.extension

import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconArrowRightFromBracket
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillPersonRunning
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillCircleQuestion
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillGear
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillUser
import com.captures2024.soongan.core.designsystem.ui.theme.SGColor
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.model.ProfileBottomSheetMenuItem

fun ProfileBottomSheetMenuItem.getStringResId() = when (this) {
    ProfileBottomSheetMenuItem.EDIT -> R.string.profile_menu_item_edit_title
    ProfileBottomSheetMenuItem.FAQ -> R.string.profile_menu_item_faq_title
    ProfileBottomSheetMenuItem.PUSH -> R.string.profile_menu_item_notification_title
    ProfileBottomSheetMenuItem.TERMS_AND_POLICY -> R.string.profile_menu_item_temp_and_policy_title
    ProfileBottomSheetMenuItem.WITHDRAW -> R.string.profile_menu_item_withdraw_title
    ProfileBottomSheetMenuItem.SIGN_OUT -> R.string.profile_menu_item_sign_out_title
}

fun ProfileBottomSheetMenuItem.getColor() = when (this) {
    ProfileBottomSheetMenuItem.SIGN_OUT -> SGColor.negative
    else -> SGColor.black
}

fun ProfileBottomSheetMenuItem.getIcon() = when (this) {
    ProfileBottomSheetMenuItem.EDIT -> MyIconPack.IconNonFillUser
    ProfileBottomSheetMenuItem.FAQ -> MyIconPack.IconNonFillCircleQuestion
    ProfileBottomSheetMenuItem.PUSH -> MyIconPack.IconNonFillGear
    ProfileBottomSheetMenuItem.TERMS_AND_POLICY -> MyIconPack.IconNonFillFile
    ProfileBottomSheetMenuItem.WITHDRAW -> MyIconPack.IconFillPersonRunning
    ProfileBottomSheetMenuItem.SIGN_OUT -> MyIconPack.IconArrowRightFromBracket
}
