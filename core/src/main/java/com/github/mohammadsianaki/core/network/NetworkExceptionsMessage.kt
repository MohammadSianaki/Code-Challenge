package com.github.mohammadsianaki.core.network

import com.github.mohammadsianaki.core.network.NetworkExceptionsMessage.NO_INTERNET_CONNECTION
import java.io.IOException

object NetworkExceptionsMessage {
    const val NO_INTERNET_CONNECTION = "خطا در برقراری ارتباط، اتصال شبکه خود را بررسی کنید"
    const val UNKNOWN = "خطای ناشناخته"
    const val UNAUTHORIZED = "توکن شما نامعتبر است، لطفا مجدداً وارد شوید."
    const val INTERNAL_SERVER = "مشکلی در سرور پیش آمده"
    const val RESOURCE_NOT_FOUND = "یافت نشد"
}

