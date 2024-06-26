package com.example.newsappmvvm.repose

import com.example.newsappmvvm.data.api.RetrofitInstanse
import com.example.newsappmvvm.data.local.NewsDao
import com.example.newsappmvvm.model.CarsResponse
import com.example.newsappmvvm.model.Article
import retrofit2.Response

class NewsRepositery(private val dao: NewsDao) {
    suspend fun getHomeCars(q: String, api: String): Response<CarsResponse> {
        return RetrofitInstanse.api.getHomeCars(q, api)
    }

    suspend fun getTrendyCars(q: String, api: String): Response<CarsResponse> {
        return RetrofitInstanse.api.getTrendyCars(q, api)
    }

    suspend fun getSearchCars(q: String, api: String): Response<CarsResponse> {
        return RetrofitInstanse.api.getSearchCars(q, api)
    }

    suspend fun insertNew(article: Article) {
        dao.insertNews(article)
    }

   suspend fun getFavNew(): List<Article> {
        return dao.getAllFavouriteNews()
    }

    suspend fun isExisted(articleUrl: String): Boolean {
        return dao.isExisted(articleUrl)
    }

    suspend fun deleteNews(articleUrl: String) {
        dao.deleteNews(articleUrl)
    }
}
