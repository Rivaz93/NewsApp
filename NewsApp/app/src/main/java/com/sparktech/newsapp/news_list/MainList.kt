package com.sparktech.newsapp.news_list

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.sparktech.newsapp.R
import kotlinx.android.synthetic.main.main_list.view.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class MainList : Controller() {

    var viewModel: MainListViewModel = MainListViewModel()
    var subscriptions: CompositeSubscription = CompositeSubscription()
    val adapter: NewsAdapter = NewsAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.main_list, container, false)

        view.main_rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            clearOnScrollListeners()
        }
        initBinding(view.main_rv)
        view.main_rv.adapter = adapter


        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        subscriptions.unsubscribe()
    }

    fun initBinding(rv: RecyclerView) {

        val linearLayout = rv.layoutManager as LinearLayoutManager
        val infiniteScrollObservable = Observable.create<Void> { subscriber ->
            rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    subscriber.onNext(null)
                    val totalItemCount = linearLayout.itemCount
                    val visibleItemCount = linearLayout.childCount
                    val firstVisibleItem = linearLayout.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                        subscriber.onNext(null)
                    }
                }
            })
        }
        subscriptions.addAll(
                // Bind list of posts to the RecyclerView
                viewModel.postsObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(({
                            adapter.addNews(it)
                        })),
                infiniteScrollObservable.subscribe {
                    subscriptions.add(
                            viewModel.requestNews().subscribe())
                }

//                // Bind loading status to show/hide loading spinner
//                viewModel?.isLoadingObservable()?.observeOn(AndroidSchedulers.mainThread())?.subscribe(({ adapter. }))
        )

    }


}