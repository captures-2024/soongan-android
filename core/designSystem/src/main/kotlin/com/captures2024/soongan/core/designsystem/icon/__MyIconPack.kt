package com.captures2024.soongan.core.designsystem.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconAwards
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconBack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconBack2
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconCircleCheck
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconCircleCheckFail
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconContentInfo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFeed
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconHome
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNextPage
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconPlus
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconPlusBig
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconPlusWhite
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconProfile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconTopArrow
import com.captures2024.soongan.core.designsystem.icon.myiconpack.Logo
import com.captures2024.soongan.core.designsystem.icon.myiconpack.LogoApple
import com.captures2024.soongan.core.designsystem.icon.myiconpack.LogoGoogle
import com.captures2024.soongan.core.designsystem.icon.myiconpack.LogoKakao
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(IconAwards, IconBack2, LogoGoogle, IconCircleCheck, IconBack, IconTopArrow,
      IconProfile, IconFilter, IconFeed, LogoApple, IconCircleCheckFail, IconHome, LogoKakao, IconPlus, IconNextPage, IconContentInfo, Logo, IconPlusWhite, IconPlusBig)
    return __AllIcons!!
  }
