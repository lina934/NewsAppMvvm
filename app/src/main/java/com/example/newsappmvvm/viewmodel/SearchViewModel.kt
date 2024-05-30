package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.model.CarsResponse
import com.example.newsappmvvm.repose.NewsRepositery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {
    val repo : NewsRepositery
    init {
        repo = NewsRepositery()
    }

    private var __newsSearch = MutableLiveData<CarsResponse>()
    val newsSearch : LiveData<CarsResponse>
        get() = __newsSearch


    fun newsSearchFun(q:String,api:String){
        viewModelScope.launch(Dispatchers.IO){
            var response = repo.getSearchCars(q,api)
            if (response != null){
                if (response.isSuccessful){
                    __newsSearch.postValue(response.body())
                }
            }
        }
    }

}