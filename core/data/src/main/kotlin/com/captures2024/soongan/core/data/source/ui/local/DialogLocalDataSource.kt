package com.captures2024.soongan.core.data.source.ui.local

import kotlinx.coroutines.flow.StateFlow

interface DialogLocalDataSource {

    val isShowGuestModeDialog: StateFlow<Boolean>

    fun setIsShowGuestModeDialog(condition: Boolean)
}
