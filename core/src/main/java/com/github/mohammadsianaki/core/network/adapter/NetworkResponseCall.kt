package com.github.mohammadsianaki.core.network.adapter

import android.util.Log
import com.google.gson.JsonArray
import com.github.mohammadsianaki.core.network.*
import com.github.mohammadsianaki.core.utils.GSON.gson
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class NetworkResponseCall<S : Any>(
    private val delegate: Call<S>
) : Call<NetworkResponse<S>> {
    override fun enqueue(callback: Callback<NetworkResponse<S>>) {
        try {
            delegate.enqueue(object : Callback<S> {
                override fun onResponse(call: Call<S>, response: Response<S>) {
                    if (response.isSuccessful) {
                        try {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(NetworkResponse.Success(response.body()!!))
                            )
                        } catch (e: Exception) {
                            callback.onResponse(
                                this@NetworkResponseCall, Response.success(asReadableException(e))
                            )
                        }
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall, Response.success(
                                extractHttpExceptions(HttpException(response))
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<S>, throwable: Throwable) {
                    callback.onResponse(
                        this@NetworkResponseCall, Response.success(asReadableException(throwable))
                    )
                }
            })
        } catch (ex: Exception) {
            callback.onResponse(this, Response.success(asReadableException(ex)))
        }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun timeout(): Timeout = delegate.timeout()

    override fun execute(): Response<NetworkResponse<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    private fun asReadableException(ex: Throwable): NetworkResponse<S> {
        return when (ex) {
            is IOException -> {
                NetworkResponse.NetworkError(ErrorHolder.NetworkConnection())
            }
            is HttpException -> extractHttpExceptions(ex)
            else -> NetworkResponse.UnExpected(ErrorHolder.Unknown(NetworkExceptionsMessage.UNKNOWN))
        }.also {
            Log.d(TAG, "asReadableException: NetworkResponse =  $it")
        }
    }

    private fun extractHttpExceptions(exception: HttpException): NetworkResponse<S> {
        return when (exception.code()) {
            ErrorCodes.BAD_REQUEST.code -> {
                val errorEntity =
                    extractErrorEntityFromResponseBody(exception.response()?.errorBody()?.string())
                NetworkResponse.HttpError(
                    ErrorHolder.BadRequest(
                        errorEntity?.errorMessage ?: (exception.message ?: "")
                    )
                )
            }
            ErrorCodes.INTERNAL_SERVER.code -> NetworkResponse.HttpError(
                ErrorHolder.InternalServerError(NetworkExceptionsMessage.INTERNAL_SERVER)
            )
            ErrorCodes.UNAUTHORIZED.code -> {
                NetworkResponse.HttpError(
                    ErrorHolder.UnAuthorized()
                )
            }
            ErrorCodes.NOT_FOUND.code -> NetworkResponse.HttpError(
                ErrorHolder.ResourceNotFound(NetworkExceptionsMessage.RESOURCE_NOT_FOUND)
            )
            else -> NetworkResponse.HttpError(
                ErrorHolder.Unknown(NetworkExceptionsMessage.UNKNOWN)
            )
        }.also {
            Log.d(TAG, "extractHttpExceptions() called with NetworkResponse.HttpError = $it")
        }
    }

    private fun extractErrorEntityFromResponseBody(errorBody: String?): HttpErrorEntity? {
        return try {
            gson.fromJson(errorBody, HttpErrorEntity::class.java)
        } catch (ex: Exception) {
            val errorArray = try {
                gson.fromJson(errorBody, JsonArray::class.java)
            } catch (ex: Exception) {
                JsonArray()
            }
            gson.fromJson(errorArray.get(0), HttpErrorEntity::class.java)
        }
    }

    companion object {
        const val TAG = "<<<NetworkResponse>>>"
    }

}