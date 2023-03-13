package com.example.newsapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)
