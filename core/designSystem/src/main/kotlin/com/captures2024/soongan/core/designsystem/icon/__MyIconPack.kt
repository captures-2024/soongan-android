package com.captures2024.soongan.core.designsystem.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillCheck
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillError
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterNew
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterOld
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoApple
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoGoogle
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconLogoKakao
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillBackArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillComment
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillEdit
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillHeart
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillInfo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillMenu
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPaperDelete
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillPlus
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillReport
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillRightArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillTopArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonSelectedProfile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconSelectedProfile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo
import kotlin.collections.List as ____KtList

public object MyIconPack

private var allIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
    get() {
        if (allIcons != null) {
            return allIcons!!
        }
        allIcons = listOf(IconFillCheck, IconSelectedHome, IconNonSelectedAwards, IconNonFillFillter,
            IconFilterLike, IconFilterNew, IconFilterOld, IconNonFillTopArrow, IconSelectedFeed, IconNonFillPlus,
            IconNonFillInfo, IconNonFillHeart, IconSelectedAwards, IconNonSelectedFeed, IconFillError,
            IconNonFillComment, IconNonFillEdit, IconNonFillBackArrow, IconFillHeart,
            IconSelectedProfile, IconLogoGoogle, IconLogoApple,
            IconNonFillPaperDelete, IconNonSelectedProfile, IconNonFillMenu, IconLogoKakao,
            IconNonFillLeftArrow, IconNonFillRightArrow, IconNonSelectedHome, IconNonFillReport, Logo,)
        return allIcons!!
    }
