package com.github.mohammadsianaki.core.utils

import com.google.gson.GsonBuilder

object GSON {
    val gson by lazy { GsonBuilder().create() }
}