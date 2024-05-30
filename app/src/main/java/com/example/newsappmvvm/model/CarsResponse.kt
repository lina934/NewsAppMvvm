package com.example.newsappmvvm.model

data class CarsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)