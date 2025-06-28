package com.captures2024.soongan.domain.usecase.system.impl.dialog

import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.domain.repository.system.DialogRepository
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import javax.inject.Inject

class PostSingleButtonDialogUseCaseImpl
@Inject
constructor(
    private val repository: DialogRepository,
) : PostSingleButtonDialogUseCase {

    override suspend fun invoke(type: CommonDialogType) {
        repository.postSingleButtonDialog(type)
    }
}
