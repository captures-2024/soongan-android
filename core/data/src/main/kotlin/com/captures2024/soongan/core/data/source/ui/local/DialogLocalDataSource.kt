package com.captures2024.soongan.core.data.source.ui.local

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface DialogLocalDataSource {

    val commonSingleButtonDialogContent: SharedFlow<String>

    val isShowGuestModeDialog: StateFlow<Boolean>

    fun setIsShowGuestModeDialog(condition: Boolean)

    suspend fun postSingleButtonDialog(content: String)
}
