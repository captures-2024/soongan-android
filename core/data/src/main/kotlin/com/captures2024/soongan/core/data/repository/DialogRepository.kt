package com.captures2024.soongan.core.data.repository

import kotlinx.coroutines.flow.StateFlow

interface DialogRepository {

    val isShowGuestModeDialogFlow: StateFlow<Boolean>

    fun setIsShowGuestModeDialogFlow(condition: Boolean)
}
