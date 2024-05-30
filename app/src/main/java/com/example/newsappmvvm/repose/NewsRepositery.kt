package com.example.newsappmvvm.repose

import com.example.newsappmvvm.data.api.RetrofitInstanse
import com.example.newsappmvvm.model.CarsResponse
import retrofit2.Response

class NewsRepositery {
    suspend fun getHomeCars(q : String,api : String):Response<CarsResponse>{
        return RetrofitInstanse.api.getHomeCars(q,api)
    }

    suspend fun getTrendyCars(q : String,api: String): Response<CarsResponse>{
        return RetrofitInstanse.api.getTrendyCars(q,api)
    }

    suspend fun getSearchCars(q: String,api: String):Response<CarsResponse>{
        return RetrofitInstanse.api.getSearchCars(q,api)
    }
}