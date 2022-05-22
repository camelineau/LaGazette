package com.example.lagazette

import com.example.lagazette.Models.NewsHeadlines

interface SelectListener {
    fun OnNewsClicked(headlines: NewsHeadlines?)
}