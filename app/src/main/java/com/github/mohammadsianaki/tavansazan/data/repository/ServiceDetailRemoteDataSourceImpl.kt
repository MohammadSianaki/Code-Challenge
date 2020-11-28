package com.github.mohammadsianaki.tavansazan.data.repository

import com.github.mohammadsianaki.core.functional.Result
import com.github.mohammadsianaki.core.functional.asResult
import com.github.mohammadsianaki.core.functional.map
import com.github.mohammadsianaki.tavansazan.data.api.home.WebService
import com.github.mohammadsianaki.tavansazan.data.toServiceDetailEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.ServiceDetailEntity
import com.github.mohammadsianaki.tavansazan.domain.repository.ServiceDetailRemoteDataSource
import javax.inject.Inject

class ServiceDetailRemoteDataSourceImpl @Inject constructor(
    private val webService: WebService
) : ServiceDetailRemoteDataSource {
    override suspend fun fetchServiceDetail(): Result<ServiceDetailEntity> {
        return webService.fetchServiceDetail().asResult().map { it.toServiceDetailEntity() }
    }
}