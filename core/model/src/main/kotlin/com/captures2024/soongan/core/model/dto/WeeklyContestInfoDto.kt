package com.captures2024.soongan.core.model.dto

data class WeeklyContestInfoDto(
    val id: Long = -1L,
    val round: Int = -1,
    val subject: String = "",
    val startAt: String = "",
    val endAt: String = "",
    val voteStartAt: String = "",
    val voteEndAt: String = "",
    val announcedAt: String = "",
)
