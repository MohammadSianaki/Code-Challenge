package com.github.mohammadsianaki.core.functional

import com.github.mohammadsianaki.core.network.ErrorHolder
import com.github.mohammadsianaki.core.network.NetworkResponse
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val failure: ErrorHolder) : Result<Nothing>()
}

@OptIn(ExperimentalContracts::class)
inline fun <T> Result<T>.fold(
    ifSuccess: (T) -> Unit = { _ -> }, ifFailure: (error: ErrorHolder) -> Unit = { _ -> }
) {
    contract {
        callsInPlace(ifSuccess, InvocationKind.AT_MOST_ONCE)
        callsInPlace(ifFailure, InvocationKind.AT_MOST_ONCE)
    }
    when (this) {
        is Result.Success -> ifSuccess(value)
        is Result.Failure -> ifFailure(failure)
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <V, U> Result<V>.map(transform: (V) -> U): Result<U>? {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Failure -> this
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <V, U> Result<V>.flatMap(transform: (V) -> Result<U>): Result<U> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Result.Success -> transform(value)
        is Result.Failure -> this
    }
}

inline fun <V, U> Result<V>.doOnSuccess(block: (V) -> U): U {
    return when (this) {
        is Result.Success -> block(value)
        else -> throw IllegalStateException()
    }
}

@OptIn(ExperimentalContracts::class)
fun <V> Result<V>.unwrap(): V {
    contract {
        returns() implies (this@unwrap is Result.Success<V>)
    }

    return when (this) {
        is Result.Success -> value
        is Result.Failure -> throw UnsupportedOperationException("called Result.unwrap on an Failure value $failure")
    }
}

@OptIn(ExperimentalContracts::class)
fun <V> Result<V>.get(): V? {
    contract {
        returnsNotNull() implies (this@get is Result.Success<V>)
        returns(null) implies (this@get is Result.Failure)
    }

    return when (this) {
        is Result.Success<V> -> value
        is Result.Failure -> null
    }
}

fun <Success> NetworkResponse<Success>.asResult(): Result<Success> {
    return when (this) {
        is NetworkResponse.Success -> Result.Success(body)
        is NetworkResponse.NetworkError -> Result.Failure(errorHolder)
        is NetworkResponse.HttpError -> Result.Failure(errorHolder)
        is NetworkResponse.UnExpected -> Result.Failure(errorHolder)
    }
}