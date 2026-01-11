package com.captures2024.soongan.data.source.comments.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.source.comments.CommentsRemoteDataSource
import javax.inject.Inject

class CommentsRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
): CommentsRemoteDataSource {
    init {
        analyticsHelper.d { "CommentsRemoteDataSource::init" }
    }
}
