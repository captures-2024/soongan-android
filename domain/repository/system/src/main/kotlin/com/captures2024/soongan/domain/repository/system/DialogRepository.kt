package com.captures2024.soongan.domain.repository.system

import com.captures2024.soongan.core.model.enums.CommonDialogType
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface DialogRepository {

    val singleButtonDialogEvent: SharedFlow<CommonDialogType>

    val isShowGuestModeDialogFlow: StateFlow<Boolean>

    fun setIsShowGuestModeDialogFlow(condition: Boolean)

    suspend fun postSingleButtonDialog(type: CommonDialogType)
}
