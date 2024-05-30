package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsappmvvm.R
import com.example.newsappmvvm.adapters.TrendyAdapters
import com.example.newsappmvvm.databinding.FragmentTrendyBinding
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.viewmodel.TrendViewModel


class TrendyFragment : Fragment() {
  lateinit var binding: FragmentTrendyBinding
  val viewModel : TrendViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendyBinding.inflate(inflater,container,false)

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
             },it.articles as ArrayList<Article>)
         }
     })
 }
}