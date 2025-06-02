package com.captures2024.soongan.domain.usecase.system.impl.loading

import com.captures2024.soongan.data.repository.system.LoadingRepository
import com.captures2024.soongan.domain.usecase.system.loading.IsLoadingUseCase
import javax.inject.Inject

class IsLoadingUseCaseImpl
@Inject
constructor(
    private val repository: LoadingRepository,
) : IsLoadingUseCase {

    override fun invoke(tag: String): Boolean {
        return repository.isLoading(tag)
    }
}
