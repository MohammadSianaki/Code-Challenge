package com.github.mohammadsianaki.core.utils

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val io: CoroutineContext
    val main: CoroutineContext
}