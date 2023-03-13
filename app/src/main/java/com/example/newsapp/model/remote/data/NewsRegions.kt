package com.example.newsapp.model.remote.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.intellij.lang.annotations.Language

@Entity(tableName = "NewsByRegions")
data class NewsRegions(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val author: String?,
    val url: String?,
    val image: String?,
    val publishedAt: String?,
    val content: String?,
    val language: String?,
    val region: String
)
