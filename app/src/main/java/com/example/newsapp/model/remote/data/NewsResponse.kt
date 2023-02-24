package com.example.newsapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("news")
    val news: List<News>,
    @SerializedName("status")
    val status: String
)