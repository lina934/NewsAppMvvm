package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsappmvvm.adapters.TrendyAdapters
import com.example.newsappmvvm.data.local.NewsDatabase
import com.example.newsappmvvm.databinding.FragmentTrendyBinding
import com.example.newsappmvvm.model.New
import com.example.newsappmvvm.repose.NewsRepositery
import com.example.newsappmvvm.viewmodel.TrendViewModel
import com.example.newsappmvvm.viewmodel.TrendViewModelProviderFactory


class TrendyFragment : Fragment() {
  lateinit var binding: FragmentTrendyBinding
  lateinit var viewModel : TrendViewModel
    private val database by lazy { NewsDatabase.getAppDataBase(requireContext()) }
    private val repo by lazy { NewsRepositery(database.articleDao()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendyBinding.inflate(inflater,container,false)
        var trendViewModelProviderFactory = TrendViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this,trendViewModelProviderFactory)[TrendViewModel::class.java]
        viewModel.newsTrendCarsFun("stela","a467fd4b93cd453eaaa8a072c10d5d98")
        setUpTrendy()
        // Inflate the layout for this fragment
        return binding.root
    }

 fun setUpTrendy(){
     viewModel.newsTrendy.observe(viewLifecycleOwner, Observer {
         binding.recyclerTrendy.apply {
             adapter = TrendyAdapters(context, onItemClick = {
               val action = TrendyFragmentDirections.actionTrendyFragmentToDetailsFragment(it)
                 findNavController().navigate(action)
             },it.news as ArrayList<New>)
         }
     })
 }
}