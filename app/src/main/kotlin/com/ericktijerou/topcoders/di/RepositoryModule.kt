package com.ericktijerou.topcoders.di

import com.apollographql.apollo.ApolloClient
import com.ericktijerou.topcoders.data.local.RepoDataStore
import com.ericktijerou.topcoders.data.local.UserDataStore
import com.ericktijerou.topcoders.data.local.dao.RepositoryDao
import com.ericktijerou.topcoders.data.local.dao.UserDao
import com.ericktijerou.topcoders.data.local.system.PreferencesHelper
import com.ericktijerou.topcoders.data.local.system.TopCodersDatabase
import com.ericktijerou.topcoders.data.network.RepoCloudStore
import com.ericktijerou.topcoders.data.network.UserCloudStore
import com.ericktijerou.topcoders.data.repository.repo.RepoRepositoryImpl
import com.ericktijerou.topcoders.data.repository.user.UserRepositoryImpl
import com.ericktijerou.topcoders.domain.repository.RepoRepository
import com.ericktijerou.topcoders.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideUserDataStore(
        database: TopCodersDatabase,
        userDao: UserDao,
        preferencesHelper: PreferencesHelper
    ): UserDataStore {
        return UserDataStore(
            database,
            userDao,
            preferencesHelper
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun provideRepositoryDataStore(
        database: TopCodersDatabase,
        repositoryDao: RepositoryDao,
        preferencesHelper: PreferencesHelper
    ): RepoDataStore {
        return RepoDataStore(
            database,
            repositoryDao,
            preferencesHelper
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun provideUserCloudStore(apolloClient: ApolloClient): UserCloudStore {
        return UserCloudStore(apolloClient)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideRepoCloudStore(apolloClient: ApolloClient): RepoCloudStore {
        return RepoCloudStore(apolloClient)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideUserRepository(
        local: UserDataStore,
        remote: UserCloudStore
    ): UserRepository {
        return UserRepositoryImpl(
            local,
            remote
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun provideRepoRepository(
        local: RepoDataStore,
        remote: RepoCloudStore
    ): RepoRepository {
        return RepoRepositoryImpl(
            local,
            remote
        )
    }
}