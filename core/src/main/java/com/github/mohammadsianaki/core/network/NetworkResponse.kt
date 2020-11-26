package com.github.mohammadsianaki.core.network

sealed class NetworkResponse<out S> {
    data class Success<T>(val body: T) : NetworkResponse<T>()
    data class NetworkError(val errorHolder: ErrorHolder) : NetworkResponse<Nothing>()
    data class HttpError(val errorHolder: ErrorHolder) : NetworkResponse<Nothing>()
    data class UnExpected(val errorHolder: ErrorHolder) : NetworkResponse<Nothing>()
}

operator fun <S> NetworkResponse<S>.invoke(): S? {
    return if (this is NetworkResponse.Success<S>) body else null
}
