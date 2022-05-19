package com.example.lagazette

import android.content.Context
import com.example.lagazette.Models.NewsHeadlines
import androidx.recyclerview.widget.RecyclerView
import com.example.lagazette.CustomerViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.lagazette.R
import com.squareup.picasso.Picasso

class CustomAdapter(private val context: Context, private val headlines: List<NewsHeadlines>) :
    RecyclerView.Adapter<CustomerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(
            LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.text_title.text = headlines[position].title
        holder.text_source.text = headlines[position].source!!.name
        if (headlines[position].urlToImage != null) {
            Picasso.get().load(headlines[position].urlToImage).into(holder.img_headline)
        }
    }

    override fun getItemCount(): Int {
        return headlines.size
    }
}