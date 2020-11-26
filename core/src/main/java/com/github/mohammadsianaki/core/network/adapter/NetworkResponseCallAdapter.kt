package com.github.mohammadsianaki.core.network.adapter

import com.github.mohammadsianaki.core.network.NetworkResponse
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResponseCallAdapter<SuccessResponseType : Any>(
    private val successType: Type
) : CallAdapter<SuccessResponseType, Call<NetworkResponse<SuccessResponseType>>> {

    override fun adapt(call: Call<SuccessResponseType>): Call<NetworkResponse<SuccessResponseType>> =
        NetworkResponseCall(call)

    override fun responseType(): Type = successType
}