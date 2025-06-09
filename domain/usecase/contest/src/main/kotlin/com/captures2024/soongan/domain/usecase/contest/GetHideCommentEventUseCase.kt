package com.captures2024.soongan.domain.usecase.contest

import kotlinx.coroutines.flow.SharedFlow

interface GetHideCommentEventUseCase {

    operator fun invoke(): SharedFlow<Long>
}
