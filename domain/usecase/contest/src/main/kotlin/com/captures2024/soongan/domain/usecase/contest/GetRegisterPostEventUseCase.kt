package com.captures2024.soongan.domain.usecase.contest

import kotlinx.coroutines.flow.SharedFlow

interface GetRegisterPostEventUseCase {

    operator fun invoke(): SharedFlow<Unit>
}
