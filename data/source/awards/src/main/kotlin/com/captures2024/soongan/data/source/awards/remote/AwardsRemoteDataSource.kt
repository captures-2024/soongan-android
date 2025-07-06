package com.captures2024.soongan.data.source.awards.remote

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto

interface AwardsRemoteDataSource {

    suspend fun getAwardsList(): List<AwardsDefaultDto>?
}
