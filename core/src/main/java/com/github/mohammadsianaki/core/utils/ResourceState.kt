package com.github.mohammadsianaki.core.utils

sealed class ResourceState {
    object Success : ResourceState()
    object Error : ResourceState()
    object Loading : ResourceState()
}
