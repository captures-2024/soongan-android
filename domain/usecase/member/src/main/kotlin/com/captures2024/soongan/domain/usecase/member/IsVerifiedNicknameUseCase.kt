package com.captures2024.soongan.domain.usecase.member

interface IsVerifiedNicknameUseCase {

    suspend operator fun invoke(nickname: String): Result<Boolean>
}
