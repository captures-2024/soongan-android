package com.captures2024.soongan.feature.home.utils

import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.feature.home.R

internal fun ReportType.getTextId(): Int = when(this) {
    ReportType.INAPPROPRIATE_PHOTO_OR_BEHAVIOR -> R.string.report_type_inappropriate_photo_or_behavior

    ReportType.PROFANITY_HATE_SPEECH -> R.string.report_type_profanity_hate_speech

    ReportType.COPYRIGHT_OR_PRIVACY_VIOLATION -> R.string.report_type_copyright_or_privacy_violation

    ReportType.SPAM -> R.string.report_type_spam

    ReportType.PROMOTIONAL_CONTENT -> R.string.report_type_promotional_content

    ReportType.OTHER -> R.string.report_type_other
}