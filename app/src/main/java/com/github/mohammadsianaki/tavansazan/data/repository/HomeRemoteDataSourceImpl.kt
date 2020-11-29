package com.github.mohammadsianaki.tavansazan.data.repository

import com.github.mohammadsianaki.core.functional.asResult
import com.github.mohammadsianaki.core.functional.map
import com.github.mohammadsianaki.tavansazan.data.api.WebService
import com.github.mohammadsianaki.tavansazan.data.toHomePageEntity
import com.github.mohammadsianaki.tavansazan.domain.repository.RemoteHomeDataSource
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val webService: WebService
) : RemoteHomeDataSource {

    override suspend fun fetchDashboardData() =
        webService.fetchDashboardData().asResult().map { it.toHomePageEntity() }
}