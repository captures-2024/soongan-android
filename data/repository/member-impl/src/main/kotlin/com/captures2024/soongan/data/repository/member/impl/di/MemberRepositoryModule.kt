package com.captures2024.soongan.data.repository.member.impl.di

import com.captures2024.soongan.data.repository.member.MemberRepository
import com.captures2024.soongan.data.repository.member.impl.MemberRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MemberRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMemberRepository(memberRepositoryImpl: MemberRepositoryImpl): MemberRepository
}
