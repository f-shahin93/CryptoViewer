package com.shahin.cryptoviewer.datasource.network.interceptors

import com.shahin.cryptoviewer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Accept","application/json")
            .header("X-CMC_PRO_API_KEY", BuildConfig.TOKEN)
            .build()
        return chain.proceed(request)
    }
}