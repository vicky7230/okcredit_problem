package com.vicky7230.okcredit_problem.data.db


import com.vicky7230.okcredit_problem.data.Article
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {
    override fun insertArticles(articles: ArrayList<Article>): Single<List<Long>> {
       return appDatabase.articleDao().insertArticles(articles)
    }

    override fun getArticles(source: String): Flowable<List<Article>> {
        return appDatabase.articleDao().getArticles(source)
    }

    override fun deleteArticles(source: String): Single<Int> {
        return appDatabase.articleDao().deleteArticles(source)
    }
}