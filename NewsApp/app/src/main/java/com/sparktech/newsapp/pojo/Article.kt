package com.sparktech.newsapp.pojo

import com.sparktech.newsapp.news_list.AdapterConstants
import com.sparktech.newsapp.news_list.ViewType

data class Article(var author: String?,
                   var title: String?,
                   var description: String?,
                   var url: String?,
                   var urlToImage: String?,
                   var publishedAt: String?) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}