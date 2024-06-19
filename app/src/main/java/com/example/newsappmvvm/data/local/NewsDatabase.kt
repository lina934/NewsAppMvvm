package com.example.newsappmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappmvvm.model.New

@Database(entities = [New::class], version = 3)
@TypeConverters(Converters::class)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun articleDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null


        fun getAppDataBase(context: Context): NewsDatabase {
            //   synchronized(this)
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<NewsDatabase>(
                    context.applicationContext, NewsDatabase::class.java, "news_DB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

    }
}