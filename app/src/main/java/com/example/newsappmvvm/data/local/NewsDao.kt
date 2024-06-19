package com.example.newsappmvvm.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappmvvm.model.New

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(new: New)

    @Query("select * from news")
    fun getAllFavouriteNews(): List<New>

    @Query("select exists (select 1 from news where url = :newsUrl)")
    suspend fun isExisted(newsUrl: String): Boolean

    @Query("delete from news where url = :newsUrl")
    suspend fun deleteNews(newsUrl: String)

    @Delete
    suspend fun deleteNews(new: New)
}
