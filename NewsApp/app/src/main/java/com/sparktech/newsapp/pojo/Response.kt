package com.sparktech.newsapp.pojo

data class Response(var status : String,
                    var source : String,
                    var sortBy : String,
                    var articles : List<Article>)