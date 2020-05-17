package com.vicky7230.okcredit_problem.data.db

import com.vicky7230.okcredit_problem.data.Article
import io.reactivex.Flowable
import io.reactivex.Single


interface DbHelper {

    fun insertArticles(articles: ArrayList<Article>): Single<List<Long>>

    fun getArticles(source: String): Flowable<List<Article>>

    fun deleteArticles(source: String): Single<Int>

}
