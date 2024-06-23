package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.model.CarsResponse
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.repose.NewsRepositery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(val repo: NewsRepositery) : ViewModel() {
    private val _newsDetails = MutableLiveData<CarsResponse>()
    val newsDetails: LiveData<CarsResponse> get() = _newsDetails

    private val _checkExistence = MutableLiveData<Boolean>()
    val checkExistence: LiveData<Boolean>
        get() = _checkExistence

    fun insertToFav(new: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertNew(new)
            _checkExistence.postValue(repo.isExisted(new.url))
        }
    }

    fun checkFavMovies(url: String) {
        viewModelScope.launch {
            val isExisted = repo.isExisted(url)
            _checkExistence.postValue(isExisted)
        }
    }

    fun deleteFav(newsUrl: String) {
        viewModelScope.launch {
            repo.deleteNews(newsUrl)
            _checkExistence.postValue(repo.isExisted(newsUrl))
        }
    }
}
