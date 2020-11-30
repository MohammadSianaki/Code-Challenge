package com.github.mohammadsianaki.core.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {
    val observedValues: MutableList<T> = mutableListOf()
    override fun onChanged(t: T) {
        observedValues.add(t)
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}