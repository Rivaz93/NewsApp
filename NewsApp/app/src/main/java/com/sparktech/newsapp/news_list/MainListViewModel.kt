package com.sparktech.newsapp.news_list

import com.sparktech.newsapp.data.INewsAPI
import com.sparktech.newsapp.data.NewsAPI
import com.sparktech.newsapp.pojo.Article
import com.sparktech.newsapp.pojo.Response
import rx.Observable
import rx.schedulers.Schedulers
import rx.subjects.BehaviorSubject
import java.util.*

class MainListViewModel {

    private var newsAPI: INewsAPI = NewsAPI().getAPI()
    private val postSubject = BehaviorSubject.create(ArrayList<Article>())

    fun requestNews(): Observable<Response> {
        return newsAPI
                .getNewsList("the-next-web", "latest", "33394927e1dc4041b48db7616a17482d")
                .subscribeOn(Schedulers.newThread())
                .doOnNext { response ->
                    postSubject.onNext(response.articles as ArrayList<Article>?)
                }

    }

    fun postsObservable(): Observable<ArrayList<Article>> {
        return postSubject.asObservable()
    }



}