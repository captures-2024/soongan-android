package com.captures2024.soongan.domain.usecase.system.loading

interface IsLoadingUseCase {

    operator fun invoke(tag: String): Boolean
}
