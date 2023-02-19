package ru.cft.binapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.cft.binapp.data.datasources.BinRemoteDataSource
import ru.cft.binapp.data.repository.BinRepositoryImpl
import ru.cft.binapp.data.repository.SharedPreferencesRepositoryImpl
import ru.cft.binapp.domain.repository.BinRepository
import ru.cft.binapp.domain.repository.SharedPreferencesRepository
import ru.cft.binapp.providers.network.BinRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindBinRepository(impl: BinRepositoryImpl) : BinRepository

    @Binds
    abstract fun bindBinRemoteDataSource(impl: BinRemoteDataSourceImpl) : BinRemoteDataSource

    @Binds
    abstract fun bindRepository(impl: SharedPreferencesRepositoryImpl) : SharedPreferencesRepository
}