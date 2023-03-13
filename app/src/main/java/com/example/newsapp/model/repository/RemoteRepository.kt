package com.example.newsapp.model.repository

import android.media.session.MediaSession.Token
import com.example.newsapp.model.remote.ApiService
import com.example.newsapp.model.remote.Constant.TOKEN
import com.example.newsapp.model.remote.data.Region

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

    fun getNewsbyRegion(region: String) = apiService.getNewsByRegion(region)
}