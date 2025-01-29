package com.captures2024.soongan.feature.home.state.registration_post

import android.net.Uri
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState

internal data class RegistrationPostUIState(
    val isLoading: Boolean = false,
    val currentMedia: Uri? = null,
    val title: String = "",
    val isOpenSubmitBottomSheet: Boolean = false,
    val showBackDialog: Boolean = false,
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("currentMedia", currentMedia.toString()),
        LogElementArgument("title", title),
        LogElementArgument("isOpenSubmitBottomSheet", isOpenSubmitBottomSheet.toString()),
        LogElementArgument("showBackDialog", showBackDialog.toString()),
    )
}