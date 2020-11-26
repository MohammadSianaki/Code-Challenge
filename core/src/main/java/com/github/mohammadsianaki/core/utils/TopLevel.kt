package com.github.mohammadsianaki.core.utils

import android.os.Handler
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun postDelayed(timeInMillis: Long, action: () -> Unit) {
    Handler().postDelayed({ action() }, timeInMillis)
}

suspend fun <T> withIO(block: suspend () -> T): T {
    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("<<<DEBUG>>>", "withIO uncaught exceptions: ", exception)
    }
    return withContext(Dispatchers.IO + handler) { block() }
}