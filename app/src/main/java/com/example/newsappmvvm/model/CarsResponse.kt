package com.example.newsappmvvm.model

import com.google.gson.annotations.SerializedName

data class CarsResponse(
    @SerializedName("articles")
    val news: List<New>,
    val status: String,
    val totalResults: Int
)