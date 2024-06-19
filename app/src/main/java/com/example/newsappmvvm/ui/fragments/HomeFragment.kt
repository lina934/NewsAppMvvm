package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsappmvvm.adapters.CarsAdapters
import com.example.newsappmvvm.data.local.NewsDatabase
import com.example.newsappmvvm.databinding.FragmentHomeBinding
import com.example.newsappmvvm.model.New
import com.example.newsappmvvm.repose.NewsRepositery
import com.example.newsappmvvm.viewmodel.HomeViewModel
import com.example.newsappmvvm.viewmodel.HomeViewModelProviderFactory


class HomeFragment : Fragment() {
   lateinit var binding: FragmentHomeBinding
   lateinit var viewModel : HomeViewModel
    private val database by lazy { NewsDatabase.getAppDataBase(requireContext()) }
    private val repo by lazy { NewsRepositery(database.articleDao()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        var homeViewModelProviderFactory = HomeViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this,homeViewModelProviderFactory)[HomeViewModel::class.java]
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
      },it.news as ArrayList<New>)
        }
    })
}

}