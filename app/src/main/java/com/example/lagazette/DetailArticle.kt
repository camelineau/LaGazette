package com.example.lagazette

import androidx.appcompat.app.AppCompatActivity
import com.example.lagazette.Models.NewsHeadlines
import android.widget.TextView
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DetailArticle : AppCompatActivity() {
    var headlines: NewsHeadlines? = null
    lateinit var txt_title: TextView
    lateinit var txt_author: TextView
    lateinit var txt_time: TextView
    lateinit var txt_detail: TextView
    lateinit var txt_content: TextView
    var img_news: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
        txt_title = findViewById(R.id.text_detail_title)
        txt_author = findViewById(R.id.text_detail_author)
        txt_time = findViewById(R.id.text_detail_time)
        txt_detail = findViewById(R.id.text_detail_detail)
        txt_content = findViewById(R.id.text_detail_content)
        img_news = findViewById(R.id.img_detail_news)
        headlines = intent.getSerializableExtra("data") as NewsHeadlines?

        txt_title.text = (headlines!!.title)
        txt_author.text = (headlines!!.author)
        txt_time.text = (headlines!!.publishedAt)
        txt_detail.text = (headlines!!.description)
        txt_content.text = (headlines!!.content)
        Picasso.get().load(headlines!!.urlToImage).into(img_news)
    }
}