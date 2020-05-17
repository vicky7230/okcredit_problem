package com.vicky7230.okcredit_problem.data

import com.google.gson.JsonElement
import com.vicky7230.okcredit_problem.data.db.AppDbHelper
import com.vicky7230.okcredit_problem.data.network.AppApiHelper
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AppDataManager @Inject
constructor(
    private val appApiHelper: AppApiHelper,
    private val appDbHelper: AppDbHelper
) : DataManager {

    override fun getNews(section: String): Observable<JsonElement> {
        return appApiHelper.getNews(section)
    }

    override fun insertArticles(articles: ArrayList<Article>): Single<List<Long>> {
        return appDbHelper.insertArticles(articles)
    }

    override fun getArticles(source: String): Flowable<List<Article>> {
        return appDbHelper.getArticles(source)
    }

    override fun deleteArticles(source: String): Single<Int> {
        return appDbHelper.deleteArticles(source)
    }

}