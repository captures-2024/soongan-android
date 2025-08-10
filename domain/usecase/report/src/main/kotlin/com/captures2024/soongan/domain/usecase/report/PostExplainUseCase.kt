package com.captures2024.soongan.domain.usecase.report

interface PostExplainUseCase {

    suspend operator fun invoke(
        targetId: Long,
        explain: String,
    ): Result<Boolean>
}
