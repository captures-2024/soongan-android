package com.captures2024.soongan.core.data.source.ui.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DialogLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : DialogLocalDataSource {

    private val _commonSingleButtonDialogContent = MutableSharedFlow<String>()
    override val commonSingleButtonDialogContent: SharedFlow<String>
        get() = _commonSingleButtonDialogContent

    private val _isShowGuestModeDialog = MutableStateFlow(false)
    override val isShowGuestModeDialog: StateFlow<Boolean>
        get() = _isShowGuestModeDialog.asStateFlow()

    init {
        analyticsHelper.d { "DialogLocalDataSource::Init" }
    }

    override fun setIsShowGuestModeDialog(condition: Boolean) {
        analyticsHelper.d { "setIsShowGuestModeDialog - condition: $condition" }
        _isShowGuestModeDialog.value = condition
    }

    override suspend fun postSingleButtonDialog(content: String) {
        _commonSingleButtonDialogContent.emit(content)
    }
}
