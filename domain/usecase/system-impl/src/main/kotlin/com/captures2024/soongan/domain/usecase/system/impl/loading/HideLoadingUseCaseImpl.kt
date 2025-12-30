package com.captures2024.soongan.domain.usecase.system.impl.loading

import com.captures2024.soongan.domain.repository.system.LoadingRepository
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import javax.inject.Inject

class HideLoadingUseCaseImpl
@Inject
constructor(
    private val repository: LoadingRepository,
) : HideLoadingUseCase {

    override fun invoke(tag: String) {
        repository.hideLoading(tag)
    }
}
