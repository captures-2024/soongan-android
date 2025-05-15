package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.repository.DialogRepository
import com.captures2024.soongan.core.data.source.ui.local.DialogLocalDataSource
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DialogRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val dialogLocalDataSource: DialogLocalDataSource,
) : DialogRepository {

    override val singleButtonDialogEvent: SharedFlow<String>
        get() = dialogLocalDataSource.commonSingleButtonDialogContent

    override val isShowGuestModeDialogFlow: StateFlow<Boolean>
        get() = dialogLocalDataSource.isShowGuestModeDialog

    init {
        analyticsHelper.d { "DialogRepository::init" }
    }

    override fun setIsShowGuestModeDialogFlow(condition: Boolean) {
        dialogLocalDataSource.setIsShowGuestModeDialog(condition)
    }

    override suspend fun postSingleButtonDialog(content: String) {
        dialogLocalDataSource.postSingleButtonDialog(content)
    }
}
