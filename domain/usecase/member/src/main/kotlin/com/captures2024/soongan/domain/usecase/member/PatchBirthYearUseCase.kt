package com.captures2024.soongan.domain.usecase.member

interface PatchBirthYearUseCase {

    suspend operator fun invoke(birthYear: Int): Result<Boolean>
}
