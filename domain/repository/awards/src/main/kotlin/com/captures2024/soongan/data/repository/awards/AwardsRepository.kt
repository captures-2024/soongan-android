package com.captures2024.soongan.data.repository.awards

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto

interface AwardsRepository {

    suspend fun getAwardsList(): List<AwardsDefaultDto>
}
