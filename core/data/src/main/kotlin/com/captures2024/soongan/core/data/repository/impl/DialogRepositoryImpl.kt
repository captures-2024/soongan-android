package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.repository.DialogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DialogRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : DialogRepository {
    private val _isShowGuestModeDialogFlow = MutableStateFlow(false)
    override val isShowGuestModeDialogFlow: StateFlow<Boolean>
        get() = _isShowGuestModeDialogFlow.asStateFlow()

    init {
        analyticsHelper.d { "DialogRepository::init" }
    }

    override fun setIsShowGuestModeDialogFlow(condition: Boolean) {
        _isShowGuestModeDialogFlow.value = condition
    }
}
