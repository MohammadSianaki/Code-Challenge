package com.github.mohammadsianaki.core.network

import com.google.gson.annotations.SerializedName

data class HttpErrorEntity(
    @SerializedName("message") val errorMessage: String,
    @SerializedName("error") val error: String
)