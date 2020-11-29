package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.functional.fold
import com.github.mohammadsianaki.core.ui.RecyclerViewModel
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.core.utils.withIO
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.presentation.model.toServiceDetailItemModel
import kotlinx.coroutines.launch

class ServiceDetailViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository
) : RecyclerViewModel<ServiceDetailPurchasePlansItemModel>() {
    override fun loadData() {
        _liveData.value = Resource.loading()
        viewModelScope.launch {
            withIO { appRepository.fetchServiceDetail("slug") }.fold(
                ifSuccess = {
                    _liveData.value = Resource.success(it.toServiceDetailItemModel().purchasePlans)
                }, ifFailure = { errorHolder ->
                    _liveData.value = Resource.error(errorHolder)
                }
            )
        }
    }
}