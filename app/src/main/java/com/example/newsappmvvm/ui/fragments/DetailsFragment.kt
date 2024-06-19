package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsappmvvm.R
import com.example.newsappmvvm.data.local.NewsDatabase
import com.example.newsappmvvm.databinding.FragmentDetailsBinding
import com.example.newsappmvvm.repose.NewsRepositery
import com.example.newsappmvvm.viewmodel.DetailsViewModel
import com.example.newsappmvvm.viewmodel.DetailsViewModelProviderFactory
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    val database by lazy { NewsDatabase.getAppDataBase(requireContext()) }
    val repo by lazy { NewsRepositery(database.articleDao()) }
    private var found = false

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val detailsViewModelProviderFactory = DetailsViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this, detailsViewModelProviderFactory)[DetailsViewModel::class.java]
        viewModel.checkFavMovies(args.argsDetails.url)

        binding.iconfav.setOnClickListener {
            if (found) {
                viewModel.deleteFav(args.argsDetails.url)
            } else {
                viewModel.insertToFav(args.argsDetails)
            }
        }

        setUpDetails()
        subScribeToExisted()
        return binding.root
    }

    private fun setUpDetails() {
        viewModel.newsDetails.observe(viewLifecycleOwner, Observer {
            binding.newsname.text = args.argsDetails.source.name
            binding.nwestitle.text = args.argsDetails.description
            Picasso.with(context).load(args.argsDetails.urlToImage).into(binding.image)
            binding.back.setOnClickListener {
                findNavController().popBackStack()
            }
        })

    }

    private fun subScribeToExisted() {
        viewModel.checkExistence.observe(viewLifecycleOwner, Observer { value ->
            if (value) {
                found = true
                binding.iconfav.setImageResource(R.drawable.heart_red)
            } else {
                found = false
                binding.iconfav.setImageResource(R.drawable.heart_empty)
            }
        })
    }
}
