package com.example.lagazette

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.lagazette.R

class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var text_title: TextView
    var text_source: TextView
    var img_headline: ImageView
    var cardView: CardView

    init {
        text_title = itemView.findViewById(R.id.text_title)
        text_source = itemView.findViewById(R.id.text_source)
        img_headline = itemView.findViewById(R.id.img_headline)
        cardView = itemView.findViewById(R.id.main_container)
    }
}