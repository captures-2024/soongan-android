package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.PostInfoDto

interface WeeklyContestDataSource {

    suspend fun registerPost(
        weeklyContestRound: Int,
        subject: String,
        imageFile: String,
    ): PostInfoDto?
}