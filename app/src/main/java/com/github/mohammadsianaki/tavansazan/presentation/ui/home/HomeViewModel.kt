package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.extensions.asLiveData
import com.github.mohammadsianaki.core.functional.fold
import com.github.mohammadsianaki.core.navigation.NavigationCommand
import com.github.mohammadsianaki.core.ui.RecyclerViewModel
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.core.utils.withIO
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.presentation.model.toHomePageItemModel
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.promo.PromoSection
import com.github.mohammadsianaki.tavansazan.presentation.ui.home.service.ServiceSection
import com.github.mohammadsianaki.tavansazan.presentation.utils.Ids
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : RecyclerViewModel<RecyclerData>() {
    private val __headerLiveData = MutableLiveData<HomePageHeaderItemModel>()
    val headerLiveData = __headerLiveData.asLiveData()

    override fun loadData() {
        _liveData.value = Resource.loading()
        viewModelScope.launch {
            withIO {
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

    fun onServiceItemClicked(item: ServiceSection) {
        _navigationCommands.value = NavigationCommand.ForwardTo(
            Ids.actionHomeFragmentToServiceDetailFragment,
            null
        )
    }

    fun onPromoItemClicked(item: PromoSection) {

    }
}