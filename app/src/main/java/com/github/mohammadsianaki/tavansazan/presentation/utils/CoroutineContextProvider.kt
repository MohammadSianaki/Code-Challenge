package com.github.mohammadsianaki.tavansazan.presentation.utils

import com.github.mohammadsianaki.core.utils.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider @Inject constructor() : DispatcherProvider {
    override val io: CoroutineContext by lazy { Dispatchers.IO }
    override val main: CoroutineContext by lazy { Dispatchers.Main }
}