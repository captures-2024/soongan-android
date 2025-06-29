package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.system.impl.DialogRepositoryImpl
import com.captures2024.soongan.data.repository.system.impl.LoadingRepositoryImpl
import com.captures2024.soongan.data.repository.system.impl.SystemRepositoryImpl
import com.captures2024.soongan.domain.repository.system.DialogRepository
import com.captures2024.soongan.domain.repository.system.LoadingRepository
import com.captures2024.soongan.domain.repository.system.SystemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SystemRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDialogRepository(dialogRepositoryImpl: DialogRepositoryImpl): DialogRepository

    @Binds
    @Singleton
    abstract fun bindLoadingRepository(loadingRepositoryImpl: LoadingRepositoryImpl): LoadingRepository

    @Binds
    @Singleton
    abstract fun bindSystemRepository(systemRepositoryImpl: SystemRepositoryImpl): SystemRepository
}
