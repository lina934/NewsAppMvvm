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
import com.example.newsappmvvm.databinding.FragmentFavouriteBinding
import com.example.newsappmvvm.repose.NewsRepositery
import com.example.newsappmvvm.viewmodel.FavouriteViewModel
import com.example.newsappmvvm.viewmodel.FavouriteViewModelProviderFactory

class FavouriteFragment : Fragment() {
    lateinit var binding: FragmentFavouriteBinding

    private val database by lazy { NewsDatabase.getAppDataBase(requireContext()) }
    private val repo by lazy { NewsRepositery(database.articleDao()) }
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater,container,false)

        val favouriteViewModelProviderFactory = FavouriteViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this,favouriteViewModelProviderFactory)[FavouriteViewModel::class.java]

        viewModel.getFavEnemies()


        binding.backe.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun subScribeFav(){
        viewModel.favNew.observe(viewLifecycleOwner, Observer {listenemy->
            binding.favoritesRecycler.apply {
                adapter = CarsAdapters(context,{news->
                    val action =
                    FavouriteFragmentDirections.actionFavouriteFragmentToDetailsFragment(news)
                    findNavController().navigate(action)

                }, listenemy )
            }
        })
    }
}