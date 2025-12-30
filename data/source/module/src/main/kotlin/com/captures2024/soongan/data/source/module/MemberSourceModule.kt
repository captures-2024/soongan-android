package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.member.impl.local.GuestLocalDataSourceImpl
import com.captures2024.soongan.data.source.member.impl.local.MemberLocalDataSourceImpl
import com.captures2024.soongan.data.source.member.impl.remote.MembersRemoteDataSourceImpl
import com.captures2024.soongan.data.source.member.local.GuestLocalDataSource
import com.captures2024.soongan.data.source.member.local.MemberLocalDataSource
import com.captures2024.soongan.data.source.member.remote.MembersRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MemberSourceModule {

    @Binds
    @Singleton
    abstract fun bindMemberLocalDataSource(memberLocalDataSourceImpl: MemberLocalDataSourceImpl): MemberLocalDataSource

    @Binds
    @Singleton
    abstract fun bindGuestLocalDataSource(guestLocalDataSourceImpl: GuestLocalDataSourceImpl): GuestLocalDataSource

    @Binds
    @Singleton
    abstract fun bindMembersRemoteDataSource(membersRemoteDataSourceImpl: MembersRemoteDataSourceImpl): MembersRemoteDataSource
}
