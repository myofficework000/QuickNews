package com.example.newsapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.remote.data.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNews(news: List<News>): List<Long>

    @Query("SELECT * FROM News")
    fun getNews(): LiveData<List<News>>
}