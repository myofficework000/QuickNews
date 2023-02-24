package com.example.newsapp.model.repository

import com.example.newsapp.model.remote.ApiService
import retrofit2.http.Query

class RemoteRepository(private val apiService: ApiService) {

    fun loadLatestNews() = apiService.getLatestNews()
    fun searchNews(
        keywords: String,
        start_date: String,
        end_date: String,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ) = apiService.searchNews(
        keywords,
        start_date,
        end_date,
        category,
        country,
        language
    )
}