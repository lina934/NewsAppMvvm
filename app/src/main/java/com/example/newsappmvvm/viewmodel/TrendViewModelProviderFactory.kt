package com.example.newsappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsappmvvm.repose.NewsRepositery

class TrendViewModelProviderFactory(val repo : NewsRepositery):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrendViewModel(repo) as T
    }
}