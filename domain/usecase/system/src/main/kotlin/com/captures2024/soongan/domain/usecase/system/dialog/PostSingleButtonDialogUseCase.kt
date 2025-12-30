package com.captures2024.soongan.domain.usecase.system.dialog

import com.captures2024.soongan.core.model.enums.CommonDialogType

interface PostSingleButtonDialogUseCase {

    suspend operator fun invoke(
        type: CommonDialogType,
    )
}
