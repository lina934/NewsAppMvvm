package com.example.newsappmvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappmvvm.databinding.ItemRowHomeBinding
import com.example.newsappmvvm.model.New
import com.squareup.picasso.Picasso

class CarsAdapters(
    private var context: Context,
    var onItemClick: ((New) -> Unit),
    private var listCars: List<New>
) : RecyclerView.Adapter<CarsAdapters.ViewHolder>() {
    class ViewHolder(var binding: ItemRowHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemRowHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount() = listCars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentCar = listCars[position]


            holder.binding.newsname.text = currentCar.source.name
            Picasso.with(context).load(currentCar.urlToImage).into(holder.binding.image)
            holder.binding.nwestitle.text = currentCar.author

            holder.binding.card.setOnClickListener {
                onItemClick.invoke(currentCar)
            }



    }
}