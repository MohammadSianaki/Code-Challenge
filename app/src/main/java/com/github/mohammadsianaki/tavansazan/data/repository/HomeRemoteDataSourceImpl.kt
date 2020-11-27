package com.github.mohammadsianaki.tavansazan.data.repository

import com.github.mohammadsianaki.core.functional.asResult
import com.github.mohammadsianaki.core.functional.map
import com.github.mohammadsianaki.tavansazan.data.api.home.HomeService
import com.github.mohammadsianaki.tavansazan.data.toHomePageEntity
import com.github.mohammadsianaki.tavansazan.domain.repository.RemoteHomeDataSource
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : RemoteHomeDataSource {

    override suspend fun fetchDashboardData() =
        homeService.fetchDashboardData().asResult().map { it.toHomePageEntity() }
}