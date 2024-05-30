package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsappmvvm.adapters.TrendyAdapters
import com.example.newsappmvvm.databinding.FragmentSearchBinding
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val viewModel : SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        binding.btn.setOnClickListener {
            viewModel.newsSearchFun("${binding.editText.text}", "a467fd4b93cd453eaaa8a072c10d5d98")
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
                }, it.articles as ArrayList<Article>)
            }
        })
    }
}