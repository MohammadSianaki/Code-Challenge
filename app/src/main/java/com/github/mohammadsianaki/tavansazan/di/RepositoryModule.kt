package com.github.mohammadsianaki.tavansazan.di

import com.github.mohammadsianaki.core.utils.DispatcherProvider
import com.github.mohammadsianaki.tavansazan.data.repository.AppRepositoryImpl
import com.github.mohammadsianaki.tavansazan.data.repository.HomeRemoteDataSourceImpl
import com.github.mohammadsianaki.tavansazan.data.repository.ServiceDetailRemoteDataSourceImpl
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.domain.repository.RemoteHomeDataSource
import com.github.mohammadsianaki.tavansazan.domain.repository.ServiceDetailRemoteDataSource
import com.github.mohammadsianaki.tavansazan.presentation.utils.CoroutineContextProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppRepository(repositoryImpl: AppRepositoryImpl): AppRepository

    @Binds
    abstract fun bindRemoteHomeDataSource(dataSourceImpl: HomeRemoteDataSourceImpl): RemoteHomeDataSource

    @Binds
    abstract fun bindRemoteServiceDataSource(dataSource: ServiceDetailRemoteDataSourceImpl): ServiceDetailRemoteDataSource

    @Binds
    abstract fun bindDispatcherProvider(dispatcherProvider: CoroutineContextProvider): DispatcherProvider
}