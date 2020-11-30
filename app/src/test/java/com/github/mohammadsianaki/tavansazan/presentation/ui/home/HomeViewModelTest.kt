package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.mohammadsianaki.core.functional.Result
import com.github.mohammadsianaki.core.network.ErrorHolder
import com.github.mohammadsianaki.core.test.testObserver
import com.github.mohammadsianaki.core.utils.DispatcherProvider
import com.github.mohammadsianaki.core.utils.None
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.tavansazan.TestContextProvider
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageHeaderEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePagePromoEntity
import com.github.mohammadsianaki.tavansazan.domain.entity.HomePageServiceEntity
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.presentation.model.toHomePageItemModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private val repository: AppRepository = mockk()
    private val dispatcherProvider: DispatcherProvider = TestContextProvider()
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val instantTestExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
        viewModel = HomeViewModel(repository, dispatcherProvider)
    }

    @Test
    fun `loadData with success data`() {
        coEvery { repository.fetchDashboardData() } returns Result.Success(fakedData)
        val testObserver = viewModel.liveData.testObserver()
        val headerTestObserver = viewModel.headerLiveData.testObserver()
        viewModel.loadData(None)
        testObserver.observedValues shouldBeEqualTo listOf(
            Resource.loading(),
            Resource.success(fakedData.toHomePageItemModel().items)
        )
        headerTestObserver.observedValues shouldBeEqualTo listOf(
            fakedData.toHomePageItemModel().header
        )
    }

    @Test
    fun `load data with failure`() {
        val errorHolder = ErrorHolder.NetworkConnection(
            "Network Connection Error due to Unit Testing"
        )
        coEvery { repository.fetchDashboardData() } returns Result.Failure(errorHolder)
        val testObserver = viewModel.liveData.testObserver()
        viewModel.loadData(None)
        testObserver.observedValues shouldBeEqualTo listOf(
            Resource.loading(),
            Resource.error(errorHolder)
        )
    }

    companion object {
        val fakedData = HomePageEntity(
            HomePageHeaderEntity("title", "subtitle"), listOf(
                HomePageServiceEntity(
                    false,
                    "100",
                    "carwash",
                    "carwash",
                    "subtitle",
                    "carwash is good for cars",
                    "short desc",
                    "slogan",
                    "http://google.com"
                )
            ), listOf(HomePagePromoEntity("http://google.com"))
        )
    }
}