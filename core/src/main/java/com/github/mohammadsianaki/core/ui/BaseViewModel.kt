package com.github.mohammadsianaki.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mohammadsianaki.core.extensions.asLiveData
import com.github.mohammadsianaki.core.navigation.NavigationCommand
import com.github.mohammadsianaki.core.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    abstract fun loadData()
    protected val _navigationCommands: MutableLiveData<NavigationCommand> = SingleLiveEvent()
    val navigationCommands = _navigationCommands.asLiveData()
}
