package com.captures2024.soongan.domain.repository.contest

import kotlinx.coroutines.flow.SharedFlow

interface ContentVisibilityRepository {

    val registerPostEvent: SharedFlow<Unit>

    val hidePostEvent: SharedFlow<Long>

    val hideCommentEvent: SharedFlow<Long>
}
