package com.example.newsapp.model.remote

import com.example.newsapp.model.remote.Constant.END_POINT
import com.example.newsapp.model.remote.data.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET(END_POINT)
    fun getLatestNews(): Call<NewsResponse>

}