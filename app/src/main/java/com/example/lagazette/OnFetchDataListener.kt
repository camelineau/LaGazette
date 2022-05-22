package com.example.lagazette

import com.example.lagazette.Models.NewsHeadlines

interface OnFetchDataListener<NewsApiResponse> {
    fun onFetchData(list: List<NewsHeadlines?>?, message: String?)
    fun onError(message: String?)
}