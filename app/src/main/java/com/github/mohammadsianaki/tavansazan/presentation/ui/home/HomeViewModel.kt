package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.extensions.asLiveData
import com.github.mohammadsianaki.core.functional.fold
import com.github.mohammadsianaki.core.navigation.NavigationCommand
import com.github.mohammadsianaki.core.ui.RecyclerViewModel
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.core.utils.DispatcherProvider
import com.github.mohammadsianaki.core.utils.None
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.presentation.model.toHomePageItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceCategoryItemModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel @ViewModelInject constructor(
    private val repository: AppRepository,
    private val dispatcherProvider: DispatcherProvider
) : RecyclerViewModel<RecyclerData, None>() {
    private val __headerLiveData = MutableLiveData<HomePageHeaderItemModel>()
    val headerLiveData = __headerLiveData.asLiveData()

    override fun loadData(params: None) {
        _liveData.value = Resource.loading()
        viewModelScope.launch(dispatcherProvider.main) {
            withContext(dispatcherProvider.io) {
                repository.fetchDashboardData()
            }.fold(
                ifSuccess = { pageEntity ->
                    _liveData.value = Resource.success(pageEntity.toHomePageItemModel().items)
                    __headerLiveData.value = pageEntity.toHomePageItemModel().header
                },
                ifFailure = { errorHolder ->
                    _liveData.value = Resource.error(errorHolder)
                }
            )
        }
    }

    fun onServiceItemClicked(
        item: ServiceCategoryItemModel
    ) {
        _navigationCommands.value = NavigationCommand.DirectTo(
            HomeFragmentDirections.actionHomeFragmentToServiceDetailFragment(item.slug)
        )
    }

    fun onPromoItemClicked(item: PromoItemModel) {

    }
}