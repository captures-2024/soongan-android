package com.captures2024.soongan.domain.usecase.report.impl

import com.captures2024.soongan.domain.repository.report.ReportRepository
import com.captures2024.soongan.domain.usecase.report.PostExplainUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class PostExplainUseCaseImpl
@Inject
constructor(
    private val reportRepository: ReportRepository,
) : PostExplainUseCase {

    override suspend fun invoke(
        targetId: Long,
        explain: String,
    ): Result<Boolean> = runSuspendCatching {
        val reportInfoDto = reportRepository.postExplain(
            targetId = targetId,
            explain = explain,
        )

        return@runSuspendCatching reportInfoDto
    }
}
