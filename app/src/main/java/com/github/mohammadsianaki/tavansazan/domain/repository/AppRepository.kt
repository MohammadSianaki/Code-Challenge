package com.github.mohammadsianaki.tavansazan.domain.repository

import com.github.mohammadsianaki.core.functional.Result
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageEntity

interface AppRepository {
    suspend fun fetchDashboardData(): Result<HomePageEntity>
}