package com.captures2024.soongan.data.repository.contest.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.repository.contest.ContentVisibilityRepository
import com.captures2024.soongan.data.source.contest.local.ContentVisibilityLocalDataSource
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class ContentVisibilityRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val contentVisibilityLocalDataSource: ContentVisibilityLocalDataSource,
) : ContentVisibilityRepository {

    override val registerPostEvent: SharedFlow<Unit>
        get() = contentVisibilityLocalDataSource.registerPostEvent

    override val hidePostEvent: SharedFlow<Long>
        get() = contentVisibilityLocalDataSource.hidePostEvent

    override val hideCommentEvent: SharedFlow<Long>
        get() = contentVisibilityLocalDataSource.hideCommentEvent

    init {
        analyticsHelper.d { "ContentVisibilityRepository::init" }
    }
}
