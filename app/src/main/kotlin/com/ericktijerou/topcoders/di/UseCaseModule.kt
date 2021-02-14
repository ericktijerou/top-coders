package com.ericktijerou.topcoders.di

import com.ericktijerou.topcoders.domain.repository.RepoRepository
import com.ericktijerou.topcoders.domain.repository.UserRepository
import com.ericktijerou.topcoders.domain.usecase.GetRepoListUseCase
import com.ericktijerou.topcoders.domain.usecase.GetUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserListUseCase {
        return GetUserListUseCase(userRepository)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideGetRepoUseCase(repoRepository: RepoRepository): GetRepoListUseCase {
        return GetRepoListUseCase(repoRepository)
    }
}