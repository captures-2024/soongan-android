package com.captures2024.soongan.data.source.contest.impl.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.source.contest.local.ContentVisibilityLocalDataSource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class ContentVisibilityLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : ContentVisibilityLocalDataSource {

    private val _registerPostEvent = MutableSharedFlow<Unit>()
    override val registerPostEvent: SharedFlow<Unit>
        get() = _registerPostEvent.asSharedFlow()

    private val _hidePostEvent = MutableSharedFlow<Long>()
    override val hidePostEvent: SharedFlow<Long>
        get() = _hidePostEvent.asSharedFlow()

    private val _hideCommentEvent = MutableSharedFlow<Long>()
    override val hideCommentEvent: SharedFlow<Long>
        get() = _hideCommentEvent.asSharedFlow()

    init {
        analyticsHelper.d { "ContentVisibilityLocalDataSource::Init" }
    }

    override suspend fun emitRegisterPostEvent() {
        analyticsHelper.d { "emitRegisterPostEvent" }
        _registerPostEvent.emit(Unit)
    }

    override suspend fun emitHidePostEvent(postId: Long) {
        analyticsHelper.d { "emitHidePostEvent - postId: $postId" }
        _hidePostEvent.emit(postId)
    }

    override suspend fun emitHideCommentEvent(commentId: Long) {
        analyticsHelper.d { "emitHideCommentEvent - commentId: $commentId" }
        _hideCommentEvent.emit(commentId)
    }
}
