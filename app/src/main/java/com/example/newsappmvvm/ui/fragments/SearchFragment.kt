package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsappmvvm.adapters.TrendyAdapters
import com.example.newsappmvvm.data.local.NewsDatabase
import com.example.newsappmvvm.databinding.FragmentSearchBinding
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.repose.NewsRepositery
import com.example.newsappmvvm.viewmodel.SearchViewModel
import com.example.newsappmvvm.viewmodel.SearchViewModelProviderFactory


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel : SearchViewModel
    private val database by lazy { NewsDatabase.getAppDataBase(requireContext()) }
    private val repo by lazy { NewsRepositery(database.articleDao()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        var searchViewModelProviderFactory = SearchViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this,searchViewModelProviderFactory)[SearchViewModel::class.java]
        binding.iconbtn.setOnClickListener {
            viewModel.newsSearchFun("${binding.name.text}", "a467fd4b93cd453eaaa8a072c10d5d98")
            setUpSearch()
            // Inflate the layout for this fragment
        }
        return binding.root
    }

    fun setUpSearch(){
        viewModel.newsSearch.observe(viewLifecycleOwner, Observer {
            binding.recyclersearch.apply {
                Log.e("testarticle",it.toString())
                adapter = TrendyAdapters(context, onItemClick = {
                    val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
                    findNavController().navigate(action)
                }, it.news as ArrayList<Article>)
            }
        })
    }
}