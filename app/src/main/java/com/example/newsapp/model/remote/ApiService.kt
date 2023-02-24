package com.example.newsapp.model.remote

import com.example.newsapp.model.remote.Constant.END_POINT
import com.example.newsapp.model.remote.Constant.END_POINT_SEARCH
import com.example.newsapp.model.remote.data.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET(END_POINT)
    fun getLatestNews(): Call<NewsResponse>

    @GET(END_POINT_SEARCH)
    fun searchNews(
        @Query("keywords") keywords: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("category") category: String? = null,
        @Query("country") country: String? = null,
        @Query("language") language: String? = null
    ): Call<NewsResponse>
}