package com.captures2024.soongan.core.domain.usecase.dialog

import com.captures2024.soongan.core.data.repository.DialogRepository
import com.captures2024.soongan.core.model.enums.CommonDialogType
import javax.inject.Inject

class PostSingleButtonDialogUseCase
@Inject
constructor(
    private val repository: DialogRepository,
) {

    suspend operator fun invoke(type: CommonDialogType) = repository.postSingleButtonDialog(type)
}
