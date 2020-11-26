package com.github.mohammadsianaki.core.utils

import com.github.mohammadsianaki.core.network.ErrorHolder


data class Resource<out T>(
    val resourceState: ResourceState, val data: T? = null, val failure: ErrorHolder? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceState.Success, data, null)
        }

        fun <T> error(failure: ErrorHolder): Resource<T> {
            return Resource(ResourceState.Error, null, failure)
        }

        fun <T> loading(): Resource<T> {
            return Resource(ResourceState.Loading, null, null)
        }
    }
}
