package com.captures2024.soongan.data.source.system.local

import com.captures2024.soongan.core.model.enums.CommonDialogType
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface DialogLocalDataSource {

    val commonSingleButtonDialogContent: SharedFlow<CommonDialogType>

    val isShowGuestModeDialog: StateFlow<Boolean>

    fun setIsShowGuestModeDialog(condition: Boolean)

    suspend fun postSingleButtonDialog(type: CommonDialogType)
}
