package com.example.newsappmvvm.model

import com.google.gson.annotations.SerializedName

data class CarsResponse(
    @SerializedName("articles")
    val news: List<Article>,
    val status: String,
    val totalResults: Int
)