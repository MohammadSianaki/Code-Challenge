package com.github.mohammadsianaki.core.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observeInFragment(liveData: LiveData<T>, action: (T) -> Unit = {}) {
    liveData.observe(viewLifecycleOwner, Observer(action))
}

fun Fragment.showMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}