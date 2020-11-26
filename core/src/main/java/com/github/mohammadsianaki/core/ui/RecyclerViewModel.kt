package com.github.mohammadsianaki.core.ui

import androidx.lifecycle.MutableLiveData
import com.github.mohammadsianaki.core.extensions.asLiveData
import com.github.mohammadsianaki.core.utils.Resource
import com.github.mohammadsianaki.core.ui.adapter.RecyclerData

abstract class RecyclerViewModel<Data : RecyclerData> : BaseViewModel() {
    protected val _liveData = MutableLiveData<Resource<List<Data>>>()
    val liveData = _liveData.asLiveData()
    protected val dataList:MutableList<Data> = mutableListOf()
}
