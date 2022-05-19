package com.example.lagazette.Models

import com.example.lagazette.Models.NewsHeadlines

class NewsApiResponse {
    var status: String? = null
    var totalResults = 0
    var articles: List<NewsHeadlines>? = null
}