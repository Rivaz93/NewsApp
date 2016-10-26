package com.sparktech.newsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.sparktech.newsapp.news_list.MainList

class MainActivity : AppCompatActivity() {

    private var router : Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val container = findViewById(R.id.controller_container) as ViewGroup

        router = Conductor.attachRouter(this, container, savedInstanceState)

        if (! router!!.hasRootController() ) {
            router?.setRoot(RouterTransaction.with(MainList()))
        }
    }

    override fun onBackPressed() {
        if (! router!!.handleBack()) {
            super.onBackPressed()
        }
    }
}
