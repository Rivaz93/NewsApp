package com.sparktech.newsapp.news_list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sparktech.newsapp.R
import com.sparktech.newsapp.pojo.Article
import inflate
import kotlinx.android.synthetic.main.item_post.view.*
import loadImg
import prettyTime

class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as Article)
    }

    class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_post)) {

        fun bind(item: Article) = with(itemView) {
            item_image.loadImg(item.urlToImage)
            item_title.text = item.title
            item_description.text = item.description
            item_author.text = item.author
            item_time_ago.text = item.publishedAt.prettyTime()
        }
    }
}