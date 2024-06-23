package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.repose.NewsRepositery
import kotlinx.coroutines.launch

class FavouriteViewModel(val repo : NewsRepositery):ViewModel() {
    private var __favNew = MutableLiveData<List<Article>>()
    val favNew : LiveData<List<Article>>
        get() = __favNew

    fun getFavEnemies(){
        viewModelScope.launch {
            __favNew.postValue(repo.getFavNew())
        }
    }
}