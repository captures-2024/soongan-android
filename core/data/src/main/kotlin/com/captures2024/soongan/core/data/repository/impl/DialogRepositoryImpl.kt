package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.repository.DialogRepository
import com.captures2024.soongan.core.data.source.ui.local.DialogLocalDataSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DialogRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val dialogLocalDataSource: DialogLocalDataSource,
) : DialogRepository {
    override val isShowGuestModeDialogFlow: StateFlow<Boolean>
        get() = dialogLocalDataSource.isShowGuestModeDialog

    init {
        analyticsHelper.d { "DialogRepository::init" }
    }

    override fun setIsShowGuestModeDialogFlow(condition: Boolean) {
        dialogLocalDataSource.setIsShowGuestModeDialog(condition)
    }
}
