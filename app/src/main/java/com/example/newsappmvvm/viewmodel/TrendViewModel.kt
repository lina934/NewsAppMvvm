package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.model.CarsResponse
import com.example.newsappmvvm.repose.NewsRepositery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendViewModel(val repo : NewsRepositery): ViewModel() {


    private var __newsTrendy = MutableLiveData<CarsResponse>()
    val newsTrendy : LiveData<CarsResponse>
        get() = __newsTrendy

    fun newsTrendCarsFun(q : String,api : String){
        viewModelScope.launch(Dispatchers.IO){
            var response = repo.getTrendyCars(q, api)
            if (response != null){
                if (response.isSuccessful){
                    __newsTrendy.postValue(response.body())
                }
            }
        }

    }
}