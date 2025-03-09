package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface HomeDataSource {

    suspend fun getHomeStatus(): Pair<ContestInfoDto, List<PostInfoDto>>?
}
