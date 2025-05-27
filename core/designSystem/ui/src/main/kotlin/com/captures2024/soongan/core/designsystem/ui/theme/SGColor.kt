package com.captures2024.soongan.core.designsystem.ui.theme

import androidx.compose.ui.graphics.Color

object SGColor {
    val primaryA = Color(0xFF252525)
    val primaryB = Color(0xFFF5F5F5)

    val accent = Color(0xFFFBC304)

    val positive = Color(0xFF276EF1)
    val negative = Color(0xFFDE1135)

    val tempPrimaryC = Color(0xFFBEBEBE)
    val tempPrimaryD = Color(0xFFD9D9D9)

    val tempNotificationBody = Color(0xFF555555)

    val buttonDisableGray = Color(0xFFBBBBBB)
    val hintGray = Color(0xFFCACACA)

    val black = Color(0xFF000000)
    val white = Color(0xFFFFFFFF)
    val transparent = Color(0x00000000)

    object Main {
        val primary = Color(0xFFFBC304)
        val secondary = Color(0xFF252525)
        val error = Color(0xFFFE2929)
    }

    object Grayscale {
        val black100 = Color(0xFF252525)
        val black80 = Color(0xFF8D939D)
        val black60 = Color(0xFFABB0BA)
        val black40 = Color(0xFFD4DAE6)
        val black20 = Color(0xFFF0F2F6)
        val white = Color(0xFFFFFFFF)
    }

    data object BG {
        val gray = Color(0xFFF0F2F6)
        val modal = Color(0xFF252525).copy(alpha = 0.5f)
        val background = Color(0xFFFAFAF8)
    }

    val black100 = Color(0xFF252525)
    val black80 = Color(0xFF8D939D)
    val black60 = Color(0xFFABB0BA)
    val black40 = Color(0xFFD4DAE6)
    val black20 = Color(0xFFF0F2F6)
}
