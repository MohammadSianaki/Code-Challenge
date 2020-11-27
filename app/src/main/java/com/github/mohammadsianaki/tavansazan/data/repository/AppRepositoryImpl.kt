package com.github.mohammadsianaki.tavansazan.data.repository

import com.github.mohammadsianaki.core.functional.Result
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageEntity
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.domain.repository.RemoteHomeDataSource
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteHomeDataSource: RemoteHomeDataSource
) : AppRepository {
    override suspend fun fetchDashboardData(): Result<HomePageEntity> {
        return remoteHomeDataSource.fetchDashboardData()
    }
}