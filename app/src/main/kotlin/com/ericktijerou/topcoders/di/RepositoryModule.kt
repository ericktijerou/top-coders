package com.ericktijerou.topcoders.di

import com.apollographql.apollo.ApolloClient
import com.ericktijerou.topcoders.data.local.UserDataStore
import com.ericktijerou.topcoders.data.local.dao.UserDao
import com.ericktijerou.topcoders.data.local.system.PreferencesHelper
import com.ericktijerou.topcoders.data.local.system.TopCodersDatabase
import com.ericktijerou.topcoders.data.network.UserCloudStore
import com.ericktijerou.topcoders.data.repository.UserRepositoryImpl
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
    fun provideUserCloudStore(apolloClient: ApolloClient): UserCloudStore {
        return UserCloudStore(apolloClient)
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
}