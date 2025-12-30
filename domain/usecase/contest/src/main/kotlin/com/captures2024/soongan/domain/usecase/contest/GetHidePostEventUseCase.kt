package com.captures2024.soongan.domain.usecase.contest

import kotlinx.coroutines.flow.SharedFlow

interface GetHidePostEventUseCase {

    operator fun invoke(): SharedFlow<Long>
}
