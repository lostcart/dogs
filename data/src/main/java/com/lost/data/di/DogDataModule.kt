package com.lost.data.di

import com.lost.data.datastore.DogCacheDataStore
import com.lost.data.datastore.DogDataStore
import com.lost.data.datastore.DogRemoteDataStore
import com.lost.data.repository.DogRepositoryImpl
import com.lost.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class DogDataModule {

    @Binds
    internal abstract fun bindRepository(impl: DogRepositoryImpl): DogRepository

    @Binds
    internal abstract fun bindRemote(impl: DogRemoteDataStore): DogDataStore.Remote

    @Binds
    internal abstract fun bindCache(impl: DogCacheDataStore): DogDataStore.Cache
}