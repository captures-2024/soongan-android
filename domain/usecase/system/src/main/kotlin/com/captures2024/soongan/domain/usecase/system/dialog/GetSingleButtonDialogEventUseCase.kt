package com.captures2024.soongan.domain.usecase.system.dialog

import com.captures2024.soongan.core.model.enums.CommonDialogType
import kotlinx.coroutines.flow.SharedFlow

interface GetSingleButtonDialogEventUseCase {

    operator fun invoke(): SharedFlow<CommonDialogType>
}
