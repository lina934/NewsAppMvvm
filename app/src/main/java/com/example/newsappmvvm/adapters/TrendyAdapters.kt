package com.example.newsappmvvm.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappmvvm.databinding.ItemRowBinding
import com.example.newsappmvvm.model.Article
import com.squareup.picasso.Picasso

class TrendyAdapters (
    private var context: Context,
    var onItemClick: ((Article) -> Unit),
    private var listCars: List<Article>
) : RecyclerView.Adapter<TrendyAdapters.ViewHolder>() {
    class ViewHolder(var binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount() = listCars.size

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentxCar = listCars[position]


            Picasso.with(context).load(currentxCar.urlToImage).into(holder.binding.image)
            holder.binding.newsname.text = currentxCar.source.name
//            holder.binding.nwestitle.text = currentxCar.author

            holder.binding.card.setOnClickListener {
                onItemClick.invoke(currentxCar)
            }



    }
}