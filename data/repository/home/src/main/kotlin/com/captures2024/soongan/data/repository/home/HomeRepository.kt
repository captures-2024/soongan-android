package com.captures2024.soongan.data.repository.home

import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface HomeRepository {

    suspend fun getHome(): Pair<HomeContestInfoDto, List<PostInfoDto>>
}
