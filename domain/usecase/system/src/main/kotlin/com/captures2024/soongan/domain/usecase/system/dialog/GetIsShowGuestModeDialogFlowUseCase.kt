package com.captures2024.soongan.domain.usecase.system.dialog

import kotlinx.coroutines.flow.StateFlow

interface GetIsShowGuestModeDialogFlowUseCase {

    operator fun invoke(): StateFlow<Boolean>
}
