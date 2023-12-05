package com.sxiaozhi.somking.api.interceptor

import com.sxiaozhi.somking.utils.SharedPrefService
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor(val sharedPrefService: SharedPrefService) : Interceptor {

    companion object {
        const val TAG = "HeaderInterceptor"
        const val HEADER_TOKEN = "token"
        const val HEADER_VERSION = "v"
        const val HEADER_PLATFORM = "platform"
        const val PLATFORM_ANDROID = "android"
        const val HEADER_DEVICE_ID = "deviceid"
        const val CHANNEL = "channel"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            addHeader("Content-Type", "application/json; charset=utf-8")
            addHeader(HEADER_VERSION, sharedPrefService.versionName)
            addHeader(HEADER_PLATFORM, PLATFORM_ANDROID)
            addHeader(HEADER_DEVICE_ID, sharedPrefService.deviceId)
//            addHeader(CHANNEL, sharedPrefService.getChannel())
            addHeader(HEADER_TOKEN, sharedPrefService.token)
        }.build()
        return chain.proceed(request)
    }
}
