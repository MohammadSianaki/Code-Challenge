package com.github.mohammadsianaki.core.ui

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun loadData()
}
