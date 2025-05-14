package com.captures2024.soongan.core.domain.usecase.dialog

import com.captures2024.soongan.core.data.repository.DialogRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetSingleButtonDialogEventUseCase
@Inject
constructor(
    private val repository: DialogRepository,
) {

    operator fun invoke(): SharedFlow<String> = repository.singleButtonDialogEvent
}
