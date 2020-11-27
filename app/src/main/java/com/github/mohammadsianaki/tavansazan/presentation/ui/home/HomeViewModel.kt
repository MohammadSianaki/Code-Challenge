package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.mohammadsianaki.core.functional.fold
import com.github.mohammadsianaki.core.ui.RecyclerViewModel
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.core.utils.withIO
import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import com.github.mohammadsianaki.tavansazan.presentation.model.toHomePageItemModel
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : RecyclerViewModel<HomePageItemModel>() {
    override fun loadData() {
        _liveData.value = Resource.loading()
        viewModelScope.launch {
            withIO {
                repository.fetchDashboardData()
            }.fold(
                ifSuccess = { pageEntity ->
                    _liveData.value = Resource.success(listOf(pageEntity.toHomePageItemModel()))
                },
                ifFailure = { errorHolder ->
                    _liveData.value = Resource.error(errorHolder)
                }
            )
        }
    }
}