package com.captures2024.soongan.core.data.repository

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface DialogRepository {

    val singleButtonDialogEvent: SharedFlow<String>

    val isShowGuestModeDialogFlow: StateFlow<Boolean>

    fun setIsShowGuestModeDialogFlow(condition: Boolean)

    suspend fun postSingleButtonDialog(content: String)
}
