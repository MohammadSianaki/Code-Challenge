package com.github.mohammadsianaki.tavansazan.di

import com.github.mohammadsianaki.tavansazan.data.repository.HomeRemoteDataSourceImpl
import com.github.mohammadsianaki.tavansazan.data.repository.AppRepositoryImpl
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.domain.repository.RemoteHomeDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppRepository(repositoryImpl: AppRepositoryImpl): AppRepository

    @Binds
    abstract fun bindRemoteHomeDataSource(dataSourceImpl: HomeRemoteDataSourceImpl): RemoteHomeDataSource
}