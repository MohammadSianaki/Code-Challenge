package com.github.mohammadsianaki.tavansazan.data.api

import com.github.mohammadsianaki.core.network.NetworkResponse
import com.github.mohammadsianaki.tavansazan.data.api.detail.ServiceDetailResponseDto
import com.github.mohammadsianaki.tavansazan.data.api.home.HomeResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("home")
    suspend fun fetchDashboardData(): NetworkResponse<HomeResponseDto>

    @GET("categories/{slug}/services")
    suspend fun fetchServiceDetail(@Path("slug") slug: String): NetworkResponse<ServiceDetailResponseDto>
}