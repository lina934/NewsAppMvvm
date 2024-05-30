package com.example.newsappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.FragmentDetailsBinding
import com.example.newsappmvvm.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    val viewModel : DetailsViewModel by viewModels()
    val args : DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
setUpDetails()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun setUpDetails(){

//            binding.newsname.text = args.argsDetails.source.name
            binding.nwestitle.text = args.argsDetails.title
            Picasso.with(context).load(args.argsDetails.urlToImage).into(binding.image)
             binding.back.setOnClickListener {
                 requireActivity().onBackPressed()
             }
    }

}