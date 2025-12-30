package com.captures2024.soongan.data.source.awards.impl.mapper

import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsListBodyResponse

internal fun GetAwardsListBodyResponse.toAwardsDefaultDto(): AwardsDefaultDto = AwardsDefaultDto(
    id = this.id,
    round = this.round,
    subject = this.subject,
    startAt = this.startAt,
    endAt = this.endAt,
    announcedAt = this.announcedAt,
    thumbnailImageUrl = this.thumbnailImageUrl,
)

internal fun AwardsDefaultDto.toGetAwardsListBodyResponse(): GetAwardsListBodyResponse = GetAwardsListBodyResponse(
    id = this.id,
    round = this.round,
    subject = this.subject,
    startAt = this.startAt,
    endAt = this.endAt,
    announcedAt = this.announcedAt,
    thumbnailImageUrl = this.thumbnailImageUrl,
)
