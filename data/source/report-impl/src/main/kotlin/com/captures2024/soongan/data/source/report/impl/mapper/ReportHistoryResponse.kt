package com.captures2024.soongan.data.source.report.impl.mapper

import com.captures2024.soongan.core.model.dto.ReportHistoryDto
import com.captures2024.soongan.core.model.network.response.report.ReportHistoryResponse

internal fun ReportHistoryResponse.toReportHistoryDto(): ReportHistoryDto = ReportHistoryDto(
    id = id,
    targetId = targetId,
    targetType = targetType,
)
