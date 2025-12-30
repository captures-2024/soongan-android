package com.captures2024.soongan.core.model.dto

import com.captures2024.soongan.core.model.enums.ContestStatus

data class HomeContestInfoDto(
    val contestType: String,
    val subject: String,
    val startAt: String,
    val endAt: String,
    val status: ContestStatus,
) {

    companion object {
        val emptyData: HomeContestInfoDto = HomeContestInfoDto(
            contestType = "",
            subject = "",
            startAt = "",
            endAt = "",
            status = ContestStatus.UPCOMING,
        )
    }
}
