package com.captures2024.soongan.data.source.contest.local

import kotlinx.coroutines.flow.SharedFlow

interface ContentVisibilityLocalDataSource {

    val registerPostEvent: SharedFlow<Unit>

    val hidePostEvent: SharedFlow<Long>

    val hideCommentEvent: SharedFlow<Long>

    suspend fun emitRegisterPostEvent()

    suspend fun emitHidePostEvent(postId: Long)

    suspend fun emitHideCommentEvent(commentId: Long)
}
