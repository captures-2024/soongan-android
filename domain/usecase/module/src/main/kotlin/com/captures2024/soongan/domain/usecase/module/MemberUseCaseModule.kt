package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.member.ClearCurrentMemberUseCase
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetGuestModeFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.member.GetMemberInfoUseCase
import com.captures2024.soongan.domain.usecase.member.IsVerifiedNicknameUseCase
import com.captures2024.soongan.domain.usecase.member.PatchBirthYearUseCase
import com.captures2024.soongan.domain.usecase.member.PatchProfileUseCase
import com.captures2024.soongan.domain.usecase.member.SetGuestModeUseCase
import com.captures2024.soongan.domain.usecase.member.impl.ClearCurrentMemberUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.GetCurrentMemberFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.GetGuestModeFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.GetIsCurrentGuestModeUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.GetMemberInfoUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.IsVerifiedNicknameUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.PatchBirthYearUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.PatchProfileUseCaseImpl
import com.captures2024.soongan.domain.usecase.member.impl.SetGuestModeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class MemberUseCaseModule {

    @Binds
    abstract fun bindClearCurrentMemberUseCase(clearCurrentMemberUseCaseImpl: ClearCurrentMemberUseCaseImpl): ClearCurrentMemberUseCase

    @Binds
    abstract fun bindGetCurrentMemberFlowUseCase(getCurrentMemberFlowUseCaseImpl: GetCurrentMemberFlowUseCaseImpl): GetCurrentMemberFlowUseCase

    @Binds
    abstract fun bindGetGuestModeFlowUseCase(getGuestModeFlowUseCaseImpl: GetGuestModeFlowUseCaseImpl): GetGuestModeFlowUseCase

    @Binds
    abstract fun bindGetIsCurrentGuestModeUseCase(getIsCurrentGuestModeUseCaseImpl: GetIsCurrentGuestModeUseCaseImpl): GetIsCurrentGuestModeUseCase

    @Binds
    abstract fun bindGetMemberInfoUseCase(getMemberInfoUseCaseImpl: GetMemberInfoUseCaseImpl): GetMemberInfoUseCase

    @Binds
    abstract fun bindIsVerifiedNicknameUseCase(isVerifiedNicknameUseCaseImpl: IsVerifiedNicknameUseCaseImpl): IsVerifiedNicknameUseCase

    @Binds
    abstract fun bindPatchBirthYearUseCase(patchBirthYearUseCaseImpl: PatchBirthYearUseCaseImpl): PatchBirthYearUseCase

    @Binds
    abstract fun bindPatchProfileUseCase(patchProfileUseCaseImpl: PatchProfileUseCaseImpl): PatchProfileUseCase

    @Binds
    abstract fun bindSetGuestModeUseCase(setGuestModeUseCaseImpl: SetGuestModeUseCaseImpl): SetGuestModeUseCase
}
