package com.captures2024.soongan.core.domain.usecase.dialog

import com.captures2024.soongan.core.data.repository.DialogRepository
import javax.inject.Inject

class SetIsShowGuestModeDialogFlowUseCase
@Inject
constructor(
    private val repository: DialogRepository,
) {

    operator fun invoke(condition: Boolean) {
        repository.setIsShowGuestModeDialogFlow(condition)
    }
}
