package com.captures2024.soongan.domain.usecase.system.impl.loading

import com.captures2024.soongan.domain.repository.system.LoadingRepository
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import javax.inject.Inject

class ShowLoadingUseCaseImpl
@Inject
constructor(
    private val repository: LoadingRepository,
) : ShowLoadingUseCase {

    override fun invoke(tag: String) {
        repository.showLoading(tag)
    }
}
