package com.vicky7230.okcredit_problem.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vicky7230.okcredit_problem.data.Article
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: ArrayList<Article>): Single<List<Long>>

    @Query("SELECT * FROM articles WHERE source =:source")
    fun getArticles(source: String): Flowable<List<Article>>

    @Query("DELETE FROM articles WHERE source =:source")
    fun deleteArticles(source: String): Single<Int>
}