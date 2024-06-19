package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsappmvvm.repose.NewsRepositery

class FavouriteViewModelProviderFactory(val repo : NewsRepositery):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(repo) as T
    }
}