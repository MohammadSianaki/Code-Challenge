package com.github.mohammadsianaki.tavansazan.domain.repository

import com.github.mohammadsianaki.core.functional.Result
import com.github.mohammadsianaki.tavansazan.domain.entity.ServiceDetailEntity

interface ServiceDetailRemoteDataSource {
    suspend fun fetchServiceDetail(slug: String): Result<ServiceDetailEntity>
}