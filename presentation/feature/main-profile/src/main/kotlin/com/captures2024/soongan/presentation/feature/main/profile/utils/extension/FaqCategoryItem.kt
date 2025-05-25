package com.captures2024.soongan.presentation.feature.main.profile.utils.extension

import androidx.annotation.StringRes
import com.captures2024.soongan.presentation.feature.main.profile.R
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategoryItem

@StringRes
internal fun FaqCategoryItem.getQuestionResId(): Int = when (this) {
    FaqCategoryItem.Default.First -> R.string.faq_data_default_q1
    FaqCategoryItem.Default.Second -> R.string.faq_data_default_q2

    FaqCategoryItem.Contest.First -> R.string.faq_data_contest_q1
    FaqCategoryItem.Contest.Second -> R.string.faq_data_contest_q2
    FaqCategoryItem.Contest.Third -> R.string.faq_data_contest_q3
    FaqCategoryItem.Contest.Fourth -> R.string.faq_data_contest_q4
    FaqCategoryItem.Contest.Fifth -> R.string.faq_data_contest_q5

    FaqCategoryItem.Copyright.First -> R.string.faq_data_copyright_q1
    FaqCategoryItem.Copyright.Second -> R.string.faq_data_copyright_q2
    FaqCategoryItem.Copyright.Third -> R.string.faq_data_copyright_q3
}

@StringRes
internal fun FaqCategoryItem.getAnswerResId(): Int = when (this) {
    FaqCategoryItem.Default.First -> R.string.faq_data_default_a1
    FaqCategoryItem.Default.Second -> R.string.faq_data_default_a2

    FaqCategoryItem.Contest.First -> R.string.faq_data_contest_a1
    FaqCategoryItem.Contest.Second -> R.string.faq_data_contest_a2
    FaqCategoryItem.Contest.Third -> R.string.faq_data_contest_a3
    FaqCategoryItem.Contest.Fourth -> R.string.faq_data_contest_a4
    FaqCategoryItem.Contest.Fifth -> R.string.faq_data_contest_a5

    FaqCategoryItem.Copyright.First -> R.string.faq_data_copyright_a1
    FaqCategoryItem.Copyright.Second -> R.string.faq_data_copyright_a2
    FaqCategoryItem.Copyright.Third -> R.string.faq_data_copyright_a3
}
