package com.captures2024.soongan.feature.profile.utils

import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconArrowRightFromBracket
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillPersonRunning
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillCircleQuestion
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillGear
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillUser
import com.captures2024.soongan.core.designsystem.theme.SGColor
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.feature.profile.R

fun ProfileBtmShtMenuItem.textId() =
    when (this) {
        ProfileBtmShtMenuItem.EDIT -> R.string.profile_menu_bottom_sheet_edit_title
        ProfileBtmShtMenuItem.FAQ -> R.string.profile_menu_bottom_sheet_faq_title
        ProfileBtmShtMenuItem.PUSH -> R.string.profile_menu_bottom_sheet_notification_title
        ProfileBtmShtMenuItem.TERMS_AND_POLICY -> R.string.profile_menu_bottom_sheet_temp_and_policy_title
        ProfileBtmShtMenuItem.WITHDRAW -> R.string.profile_menu_bottom_sheet_withdraw_title
        ProfileBtmShtMenuItem.SIGN_OUT -> R.string.profile_menu_bottom_sheet_sign_out_title
    }

fun ProfileBtmShtMenuItem.color() =
    if (this == ProfileBtmShtMenuItem.WITHDRAW) SGColor.negative else SGColor.black

fun ProfileBtmShtMenuItem.icon() =
    when (this) {
        ProfileBtmShtMenuItem.EDIT -> MyIconPack.IconNonFillUser
        ProfileBtmShtMenuItem.FAQ -> MyIconPack.IconNonFillCircleQuestion
        ProfileBtmShtMenuItem.PUSH -> MyIconPack.IconNonFillGear
        ProfileBtmShtMenuItem.TERMS_AND_POLICY -> MyIconPack.IconNonFillFile
        ProfileBtmShtMenuItem.WITHDRAW -> MyIconPack.IconFillPersonRunning
        ProfileBtmShtMenuItem.SIGN_OUT -> MyIconPack.IconArrowRightFromBracket
    }