package com.github.mohammadsianaki.tavansazan.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (alreadyHasAuthorizationHeader(originalRequest)) {
            return chain.proceed(originalRequest)
        }

        val requestBuilder = originalRequest.newBuilder()
            .header(AUTH_HEADER_KEY, "Bearer $ACCESS_TOKEN")
            .method(originalRequest.method, originalRequest.body)

        return chain.proceed(requestBuilder.build())
    }

    private fun alreadyHasAuthorizationHeader(request: Request): Boolean {
        return !request.header(AUTH_HEADER_KEY).isNullOrEmpty()
    }

    companion object {
        private const val AUTH_HEADER_KEY = "Authorization"
        private const val ACCESS_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmJmYzA3N2I4NmFkYTAwMGFkYTYxNjgiLCJpYXQiOjE2MDY0MDIxNjh9.8s3_DsCxRhqpGkODeuOnGGwcnYqjRJkX4NbZ3YVmw3M"
    }
}