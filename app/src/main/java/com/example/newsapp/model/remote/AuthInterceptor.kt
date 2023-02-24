package com.example.newsapp.model.remote

import com.example.newsapp.model.remote.Constant.AUTHORIZATION
import com.example.newsapp.model.remote.Constant.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader(AUTHORIZATION, TOKEN)

        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}