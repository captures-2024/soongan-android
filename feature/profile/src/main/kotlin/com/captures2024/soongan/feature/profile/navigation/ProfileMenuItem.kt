package com.captures2024.soongan.feature.profile.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconArrowRightFromBracket
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillPersonRunning
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillCircleQuestion
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillGear
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillUser
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.feature.profile.R

internal enum class ProfileMenuItem(
    @StringRes val titleRes: Int,
    val icon: ImageVector,
    val color: Color = PrimaryA,
) {
    EDIT(
        titleRes = R.string.profile_menu_bottom_sheet_edit_title,
        icon = MyIconPack.IconNonFillUser,
    ),
    NOTIFICATION_SETTING(
        titleRes = R.string.profile_menu_bottom_sheet_notification_title,
        icon = MyIconPack.IconNonFillGear,
    ),
    TERMS_AND_POLICY(
        titleRes = R.string.profile_menu_bottom_sheet_temp_and_policy_title,
        icon = MyIconPack.IconNonFillFile,
    ),
    FAQ(
        titleRes = R.string.profile_menu_bottom_sheet_faq_title,
        icon = MyIconPack.IconNonFillCircleQuestion,
    ),
    WITHDRAW(
        titleRes = R.string.profile_menu_bottom_sheet_withdraw_title,
        icon = MyIconPack.IconFillPersonRunning,
    ),
    SIGN_OUT(
        titleRes = R.string.profile_menu_bottom_sheet_sign_out_title,
        icon = MyIconPack.IconArrowRightFromBracket,
        color = Color(0xFFFC0000)
    )
}