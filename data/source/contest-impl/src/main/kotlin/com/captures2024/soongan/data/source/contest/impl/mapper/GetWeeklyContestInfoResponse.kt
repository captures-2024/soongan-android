package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.WeeklyContestInfoDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetWeeklyContestInfoResponse

internal fun GetWeeklyContestInfoResponse.toWeeklyContestInfoDto(): WeeklyContestInfoDto = WeeklyContestInfoDto(
    id = this.id,
    round = this.round,
    subject = this.subject,
    startAt = this.startAt,
    endAt = this.endAt,
    voteStartAt = this.voteStartAt,
    voteEndAt = this.voteEndAt,
    announcedAt = this.announcedAt,
)
