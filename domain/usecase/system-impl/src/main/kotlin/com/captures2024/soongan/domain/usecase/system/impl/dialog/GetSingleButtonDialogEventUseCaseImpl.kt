package com.captures2024.soongan.domain.usecase.system.impl.dialog

import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.domain.repository.system.DialogRepository
import com.captures2024.soongan.domain.usecase.system.dialog.GetSingleButtonDialogEventUseCase
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetSingleButtonDialogEventUseCaseImpl
@Inject
constructor(
    private val repository: DialogRepository,
) : GetSingleButtonDialogEventUseCase {

    override fun invoke(): SharedFlow<CommonDialogType> = repository.singleButtonDialogEvent
}
