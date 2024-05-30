package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.data.api.RetrofitInstanse
import com.example.newsappmvvm.model.CarsResponse
import com.example.newsappmvvm.repose.NewsRepositery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val repo : NewsRepositery
    init {
        repo = NewsRepositery()
    }

    private var __newsHome = MutableLiveData<CarsResponse>()
    val newsHome : LiveData<CarsResponse>
        get() = __newsHome

    fun carsHomeFun(q : String,api : String){
      viewModelScope.launch(Dispatchers.IO){
          var response = repo.getHomeCars(q,api)
          if (response != null){
              if (response.isSuccessful){
                  __newsHome.postValue(response.body())
              }
          }
      }

    }
}