package com.example.lagazette.Models

import com.example.lagazette.Models.NewsHeadlines
import java.io.Serializable

class NewsApiResponse : Serializable {
    var status: String? = null
    var totalResults = 0
    var articles: List<NewsHeadlines>? = null
}