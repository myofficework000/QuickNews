package com.example.newsapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.remote.data.News
import com.example.newsapp.model.remote.data.OldNews

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveNews(news: List<News>): List<Long>

    @Query("SELECT * FROM News")
    fun getNews(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveOldNews(news: List<OldNews>): List<Long>

    @Query("""
        SELECT * FROM OldNews WHERE 
            published >= :start_date AND published <= :end_date AND
            (:language IS NULL or language LIKE :language) AND
            (:category IS :category) AND
            (:country IS :country)
    """)
    fun getOldNews(
        start_date: Long,
        end_date: Long,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ): List<OldNews>
}