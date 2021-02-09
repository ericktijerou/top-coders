package com.ericktijerou.topcoders.di

import android.content.Context
import com.ericktijerou.topcoders.data.local.system.PreferencesHelper
import com.ericktijerou.topcoders.data.local.system.TopCodersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PersistenceModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        TopCodersDatabase.newInstance(context)

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context) = PreferencesHelper(context)

    @Singleton
    @Provides
    fun provideUserDao(database: TopCodersDatabase) = database.userDao()
}