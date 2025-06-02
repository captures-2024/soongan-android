package com.captures2024.soongan.data.source.system.impl.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.data.source.system.local.DialogLocalDataSource
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

    private val _commonSingleButtonDialogContent = MutableSharedFlow<CommonDialogType>()
    override val commonSingleButtonDialogContent: SharedFlow<CommonDialogType>
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

    override suspend fun postSingleButtonDialog(type: CommonDialogType) {
        _commonSingleButtonDialogContent.emit(type)
    }
}
