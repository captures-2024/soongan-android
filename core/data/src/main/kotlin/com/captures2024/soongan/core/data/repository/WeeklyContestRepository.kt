package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.PostInfoDto

interface WeeklyContestRepository {

    suspend fun registerPost(
        weeklyContestRound: Int,
        subject: String,
        imageFile: String
    ): PostInfoDto
}