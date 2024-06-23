package com.example.newsappmvvm.data.api

import com.example.newsappmvvm.model.CarsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarsApi {
    @GET("everything")
   suspend fun getHomeCars(
        @Query("q") q : String,
        @Query("apiKey") apiKey : String
    )
    :Response<CarsResponse>

    @GET("everything")
    suspend fun getTrendyCars(
        @Query("q") q : String,
        @Query("apiKey") apiKey : String
    )
    :Response<CarsResponse>

    @GET("everything")
    suspend fun getSearchCars(
        @Query("q") q : String,
        @Query("apiKey") apiKey : String
    )
            :Response<CarsResponse>
}