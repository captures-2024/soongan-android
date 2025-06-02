package com.captures2024.soongan.presentation.designsystem.ui.util.extension

import android.icu.text.CompactDecimalFormat
import java.util.Locale

/*  1_000 -> 1k, 1_000_000 -> 1m  */
fun Int.toKM(): String {
    val formatter = CompactDecimalFormat.getInstance(Locale.US, CompactDecimalFormat.CompactStyle.SHORT)

    return formatter.format(this)
}
