package com.captures2024.soongan.feature.profile.ui.faq.model

import androidx.annotation.StringRes
import com.captures2024.soongan.feature.profile.R

internal data class FAQItem(
    @StringRes val question : Int,
    @StringRes val answer: Int,
)

internal sealed class FAQData(@StringRes val titleId: Int, val faqs: List<FAQItem>) {
    data object Default : FAQData(
        titleId = R.string.faq_data_default_title,
        faqs = listOf(
            FAQItem(
                question = R.string.faq_data_default_q1,
                answer = R.string.faq_data_default_a1
            ),
            FAQItem(
                question = R.string.faq_data_default_q2,
                answer = R.string.faq_data_default_a2
            )
        )
    )

    data object Contest : FAQData(
        titleId = R.string.faq_data_contest_title,
        faqs = listOf(
            FAQItem(
                question = R.string.faq_data_contest_q1,
                answer = R.string.faq_data_contest_a1
            ),
            FAQItem(
                question = R.string.faq_data_contest_q2,
                answer = R.string.faq_data_contest_a2
            ),
            FAQItem(
                question = R.string.faq_data_contest_q3,
                answer = R.string.faq_data_contest_a3

            ),
            FAQItem(
                question = R.string.faq_data_contest_q4,
                answer = R.string.faq_data_contest_a4
            ),
            FAQItem(
                question = R.string.faq_data_contest_q5,
                answer = R.string.faq_data_contest_a5
            )
        )
    )

    data object Copyright : FAQData(
        titleId = R.string.faq_data_copyright_title,
        faqs = listOf(
            FAQItem(
                question = R.string.faq_data_copyright_q1,
                answer = R.string.faq_data_copyright_a1
            ),
            FAQItem(
                question = R.string.faq_data_copyright_q2,
                answer = R.string.faq_data_copyright_a2

            ),
            FAQItem(
                question = R.string.faq_data_copyright_q3,
                answer = R.string.faq_data_copyright_a3

            )
        )
    )
}
