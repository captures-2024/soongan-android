package com.captures2024.soongan.domain.usecase.system.loading

interface ClearLoadingUseCase {

    operator fun invoke(
        tag: String,
    )
}
