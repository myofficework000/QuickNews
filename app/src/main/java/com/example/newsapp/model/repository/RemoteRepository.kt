package com.example.newsapp.model.repository

import com.example.newsapp.model.remote.ApiService

class RemoteRepository(private val apiService: ApiService) {

    fun loadLatestNews() = apiService.getLatestNews()
}