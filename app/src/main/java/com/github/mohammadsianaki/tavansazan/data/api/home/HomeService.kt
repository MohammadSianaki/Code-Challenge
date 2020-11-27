package com.github.mohammadsianaki.tavansazan.data.api.home

import com.github.mohammadsianaki.core.network.NetworkResponse
import retrofit2.http.GET

interface HomeService {
    @GET("home")
    suspend fun fetchDashboardData(): NetworkResponse<HomeResponseDto>
}