package com.captures2024.soongan.domain.usecase.auth

interface WithdrawMemberUseCase {

    suspend operator fun invoke(): Result<Boolean>
}
