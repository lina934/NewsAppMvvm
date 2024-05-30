package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsappmvvm.adapters.CarsAdapters
import com.example.newsappmvvm.databinding.FragmentHomeBinding
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
   lateinit var binding: FragmentHomeBinding
   val viewModel : HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel.carsHomeFun("ford","a467fd4b93cd453eaaa8a072c10d5d98")
        setUpHome()
        // Inflate the layout for this fragment
        return binding.root
    }

fun setUpHome(){
    viewModel.newsHome.observe(viewLifecycleOwner, Observer {
        binding.recyclerhome.apply {
      adapter = CarsAdapters(context, onItemClick = {
          val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
          findNavController().navigate(action)
      },it.articles as ArrayList<Article>)
        }
    })
}

}