package com.captures2024.soongan.domain.usecase.system.impl.loading

import com.captures2024.soongan.domain.repository.system.LoadingRepository
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import javax.inject.Inject

class ClearLoadingUseCaseImpl
@Inject
constructor(
    private val repository: LoadingRepository,
) : ClearLoadingUseCase {

    override fun invoke(tag: String) {
        repository.clearLoading(tag)
    }
}
