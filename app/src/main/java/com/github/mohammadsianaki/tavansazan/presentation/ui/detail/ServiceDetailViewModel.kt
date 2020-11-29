package com.github.mohammadsianaki.tavansazan.presentation.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.extensions.asLiveData
import com.github.mohammadsianaki.core.functional.fold
import com.github.mohammadsianaki.core.ui.RecyclerViewModel
import com.github.mohammadsianaki.core.utils.DispatcherProvider
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.presentation.model.toServiceDetailItemModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServiceDetailViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository,
    private val dispatcherProvider: DispatcherProvider
) : RecyclerViewModel<ServiceDetailPurchasePlansItemModel, ServiceDetailFragmentArgs>() {
    private val __headerLiveData = MutableLiveData<ServiceDetailInfo>()
    val headerLiveData = __headerLiveData.asLiveData()
    override fun loadData(params: ServiceDetailFragmentArgs) {
        _liveData.value = Resource.loading()
        viewModelScope.launch(dispatcherProvider.main) {
            withContext(dispatcherProvider.io) { appRepository.fetchServiceDetail(params.slug) }.fold(
                ifSuccess = { serviceDetailEntity ->
                    with(serviceDetailEntity.toServiceDetailItemModel()) {
                        _liveData.value =
                            Resource.success(serviceDetailEntity.toServiceDetailItemModel().purchasePlans)
                        __headerLiveData.value = ServiceDetailInfo(title, descriptions, imageUrl)
                    }
                }, ifFailure = { errorHolder ->
                    _liveData.value = Resource.error(errorHolder)
                }
            )
        }
    }
}