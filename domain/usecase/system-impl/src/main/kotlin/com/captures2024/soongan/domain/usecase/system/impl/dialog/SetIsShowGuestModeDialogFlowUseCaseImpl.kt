package com.captures2024.soongan.domain.usecase.system.impl.dialog

import com.captures2024.soongan.domain.repository.system.DialogRepository
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import javax.inject.Inject

class SetIsShowGuestModeDialogFlowUseCaseImpl
@Inject
constructor(
    private val repository: DialogRepository,
) : SetIsShowGuestModeDialogFlowUseCase {

    override fun invoke(condition: Boolean) {
        repository.setIsShowGuestModeDialogFlow(condition)
    }
}
