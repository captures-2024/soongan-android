package com.captures2024.soongan.domain.usecase.system.impl.dialog

import com.captures2024.soongan.data.repository.system.DialogRepository
import com.captures2024.soongan.domain.usecase.system.dialog.GetIsShowGuestModeDialogFlowUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetIsShowGuestModeDialogFlowUseCaseImpl
@Inject
constructor(
    private val repository: DialogRepository,
) : GetIsShowGuestModeDialogFlowUseCase {

    override fun invoke(): StateFlow<Boolean> = repository.isShowGuestModeDialogFlow
}
