package com.captures2024.soongan.data.source.awards.remote

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto

interface AwardsRemoteDataSource {

    suspend fun getAwardsList(): List<AwardsDefaultDto>?

    suspend fun getAwardsDetailInfo(
        contestId: Long,
    ): AwardsDetailDto?
}
