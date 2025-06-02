package com.captures2024.soongan.domain.usecase.contest.impl.di

import com.captures2024.soongan.domain.usecase.contest.DeletePostLikeUseCase
import com.captures2024.soongan.domain.usecase.contest.DeletePostUseCase
import com.captures2024.soongan.domain.usecase.contest.EditPostTitleUseCase
import com.captures2024.soongan.domain.usecase.contest.GetFilteredGalleryByReportTargetIdsUseCase
import com.captures2024.soongan.domain.usecase.contest.GetMyGalleryUseCase
import com.captures2024.soongan.domain.usecase.contest.GetPostInfoUseCase
import com.captures2024.soongan.domain.usecase.contest.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.domain.usecase.contest.PutPostLikeUseCase
import com.captures2024.soongan.domain.usecase.contest.RegisterPostUseCase
import com.captures2024.soongan.domain.usecase.contest.impl.DeletePostLikeUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.DeletePostUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.EditPostTitleUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.GetFilteredGalleryByReportTargetIdsUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.GetMyGalleryUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.GetPostInfoUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.GetWeeklyContestInfoListUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.PutPostLikeUseCaseImpl
import com.captures2024.soongan.domain.usecase.contest.impl.RegisterPostUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ContestUseCaseModule {

    @Binds
    abstract fun bindDeletePostLikeUseCase(deletePostLikeUseCaseImpl: DeletePostLikeUseCaseImpl): DeletePostLikeUseCase

    @Binds
    abstract fun bindDeletePostUseCase(deletePostUseCaseImpl: DeletePostUseCaseImpl): DeletePostUseCase

    @Binds
    abstract fun bindEditPostTitleUseCase(editPostTitleUseCaseImpl: EditPostTitleUseCaseImpl): EditPostTitleUseCase

    @Binds
    abstract fun bindGetFilteredGalleryByReportTargetIdsUseCase(getFilteredGalleryByReportTargetIdsUseCaseImpl: GetFilteredGalleryByReportTargetIdsUseCaseImpl): GetFilteredGalleryByReportTargetIdsUseCase

    @Binds
    abstract fun bindGetMyGalleryUseCase(getMyGalleryUseCaseImpl: GetMyGalleryUseCaseImpl): GetMyGalleryUseCase

    @Binds
    abstract fun bindGetPostInfoUseCase(getPostInfoUseCaseImpl: GetPostInfoUseCaseImpl): GetPostInfoUseCase

    @Binds
    abstract fun bindGetWeeklyContestInfoListUseCase(getWeeklyContestInfoListUseCaseImpl: GetWeeklyContestInfoListUseCaseImpl): GetWeeklyContestInfoListUseCase

    @Binds
    abstract fun bindPutPostLikeUseCase(putPostLikeUseCaseImpl: PutPostLikeUseCaseImpl): PutPostLikeUseCase

    @Binds
    abstract fun bindRegisterPostUseCase(registerPostUseCaseImpl: RegisterPostUseCaseImpl): RegisterPostUseCase
}
