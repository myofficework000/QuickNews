package com.example.newsapp.model.remote.data.data

import com.example.newsapp.model.remote.data.News

data class NewsResponse(
    val news: List<News>,
    val status: String
)