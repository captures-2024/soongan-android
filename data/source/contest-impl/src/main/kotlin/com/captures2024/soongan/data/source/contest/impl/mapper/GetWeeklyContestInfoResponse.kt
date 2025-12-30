package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetWeeklyContestInfoResponse

internal fun GetWeeklyContestInfoResponse.toWeeklyContestInfoDto(): WeeklyContestInfoDto = WeeklyContestInfoDto(
    id = this.id,
    round = this.round,
    subject = this.subject,
    startAt = AppConst.EMPTY_STRING,
    endAt = AppConst.EMPTY_STRING,
    voteStartAt = AppConst.EMPTY_STRING,
    voteEndAt = AppConst.EMPTY_STRING,
    announcedAt = AppConst.EMPTY_STRING,
)
