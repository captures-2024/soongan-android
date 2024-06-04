package com.captures2024.soongan.core.designsystem.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconBack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconCircleCheck
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconCircleCheckFail
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
    __AllIcons= listOf(LogoGoogle, IconCircleCheck, IconBack, LogoApple, IconCircleCheckFail,
        LogoKakao)
    return __AllIcons!!
  }
