package com.captures2024.soongan.core.domain.usecase.loading

import com.captures2024.soongan.core.data.repository.LoadingRepository
import javax.inject.Inject

class ShowLoadingUseCase
@Inject
constructor(
    private val repository: LoadingRepository,
) {

    operator fun invoke(tag: String) = repository.showLoading(tag)
}
