package com.captures2024.soongan.core.data.source.members.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GuestLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : GuestLocalDataSource {
    private val _isGuestMode: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        analyticsHelper.d { "GuestLocalDataSource::init" }
    }

    override val isGuestMode: StateFlow<Boolean>
        get() = _isGuestMode.asStateFlow()

    override fun setGuestMode(isGuestMode: Boolean) {
        _isGuestMode.value = isGuestMode
    }
}
