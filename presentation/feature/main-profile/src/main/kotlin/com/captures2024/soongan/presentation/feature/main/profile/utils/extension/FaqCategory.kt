package com.captures2024.soongan.presentation.feature.main.profile.utils.extension

import androidx.annotation.StringRes
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategory

@StringRes
internal fun FaqCategory.getTabResId(): Int = when (this) {
    FaqCategory.DEFAULT -> R.string.faq_tab_default
    FaqCategory.CONTEST -> R.string.faq_tab_contest
    FaqCategory.COPYRIGHT -> R.string.faq_tab_copy_right
}
