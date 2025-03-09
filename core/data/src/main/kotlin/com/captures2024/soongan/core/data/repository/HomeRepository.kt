package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface HomeRepository {

    suspend fun getHome(): Pair<ContestInfoDto, List<PostInfoDto>>
}
