package com.example.newsappmvvm.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappmvvm.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: Article)

    @Query("select * from articles")
    suspend fun getAllFavouriteNews(): List<Article>

    @Query("select exists (select 1 from articles where url = :newsUrl)")
    suspend fun isExisted(newsUrl: String): Boolean

    @Query("delete from articles where url = :newsUrl")
    suspend fun deleteNews(newsUrl: String)

    @Delete
    suspend fun deleteNews(article: Article)
}
