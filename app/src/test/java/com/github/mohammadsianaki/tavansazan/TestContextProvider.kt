package com.github.mohammadsianaki.tavansazan

import com.github.mohammadsianaki.core.utils.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
class TestContextProvider : DispatcherProvider {
    override val io: CoroutineContext = TestCoroutineDispatcher()
    override val main: CoroutineContext = TestCoroutineDispatcher()
}