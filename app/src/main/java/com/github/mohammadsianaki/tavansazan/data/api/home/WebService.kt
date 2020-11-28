package com.github.mohammadsianaki.tavansazan.data.api.home

import com.github.mohammadsianaki.core.network.NetworkResponse
import com.github.mohammadsianaki.tavansazan.data.api.detail.ServiceDetailResponseDto
import retrofit2.http.GET

interface WebService {
    @GET("home")
    suspend fun fetchDashboardData(): NetworkResponse<HomeResponseDto>

    @GET("")
    suspend fun fetchServiceDetail():NetworkResponse<ServiceDetailResponseDto>
}