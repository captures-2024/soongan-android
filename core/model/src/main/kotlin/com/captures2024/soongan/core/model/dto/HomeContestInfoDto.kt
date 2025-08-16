package com.captures2024.soongan.core.model.dto

data class HomeContestInfoDto(
    val contestType: String,
    val subject: String,
    val startAt: String,
    val endAt: String,
) {

    companion object {
        val emptyData: HomeContestInfoDto = HomeContestInfoDto(
            contestType = "",
            subject = "",
            startAt = "",
            endAt = "",
        )
    }
}
