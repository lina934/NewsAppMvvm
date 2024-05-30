package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappmvvm.model.CarsResponse
import com.example.newsappmvvm.repose.NewsRepositery

class DetailsViewModel:ViewModel() {
    val repo : NewsRepositery
    init {
        repo = NewsRepositery()
    }

    private var __newsDetails = MutableLiveData<CarsResponse>()
    val newsDetails : LiveData<CarsResponse>
        get() = __newsDetails
}