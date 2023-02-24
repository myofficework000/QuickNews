package com.example.newsapp.model.remote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.utils.formatDate
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

@Entity(tableName = "OldNews")
data class OldNews(
    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val language: String,

    @ColumnInfo(name = "published")
    @SerializedName("published")
    val published: Long,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String
) {
    companion object {
        fun fromNews(news: News) = OldNews(
            id = news.id,
            title = news.title,
            description = news.description,
            author = news.author,
            url = news.url,
            language = news.language,
            image = news.image,
            published = news.published.formatDate()
        )
    }
}
