package com.captures2024.soongan.data.repository.awards

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto

interface AwardsRepository {

    suspend fun getAwardsList(): List<AwardsDefaultDto>

    suspend fun getAwardsInfo(
        awardsId: Long,
    ): AwardsDetailDto
}
