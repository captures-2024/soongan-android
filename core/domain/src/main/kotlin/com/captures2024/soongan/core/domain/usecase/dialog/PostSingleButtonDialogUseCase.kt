package com.captures2024.soongan.core.domain.usecase.dialog

import com.captures2024.soongan.core.data.repository.DialogRepository
import javax.inject.Inject

class PostSingleButtonDialogUseCase
@Inject
constructor(
    private val repository: DialogRepository,
) {

    suspend operator fun invoke(content: String) = repository.postSingleButtonDialog(content)
}
