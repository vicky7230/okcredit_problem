package com.vicky7230.okcredit_problem.ui.news

import androidx.lifecycle.MutableLiveData
import com.vicky7230.okcredit_problem.data.Article
import com.vicky7230.okcredit_problem.data.DataManager
import com.vicky7230.okcredit_problem.data.State
import com.vicky7230.okcredit_problem.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    val state = MutableLiveData<State<ArrayList<Article>>>()

    fun getNewsFromDb(source: String) {
        state.value = State.Loading
        compositeDisposable.add(
            dataManager.getArticles(source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        state.value = State.Success(it as ArrayList<Article>)
                    } else {
                        getNewsFromNetwork(source)
                    }
                }, {
                    state.value = State.Error(IOException(it))
                    Timber.e(it)
                })
        )
    }

    private fun getNewsFromNetwork(source: String) {
        state.value = State.Loading

        compositeDisposable.add(
            dataManager.getNews(source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val results = it.asJsonObject["results"].asJsonArray
                    val articles = arrayListOf<Article>()
                    results.forEach {
                        val imageUrl = if (it.asJsonObject["multimedia"].isJsonArray)
                            it.asJsonObject["multimedia"].asJsonArray[0].asJsonObject["url"].asString
                        else
                            ""
                        articles.add(
                            Article(
                                0,
                                source,
                                it.asJsonObject["title"].asString,
                                it.asJsonObject["abstract"].asString,
                                it.asJsonObject["byline"].asString,
                                it.asJsonObject["published_date"].asString,
                                it.asJsonObject["url"].asString,
                                imageUrl
                            )
                        )
                    }

                    insetArticlesInDb(articles)
                }, {
                    state.value = State.Error(IOException(it))
                    Timber.e(it)
                })

        )
    }

    private fun insetArticlesInDb(articles: java.util.ArrayList<Article>) {
        state.value = State.Loading

        compositeDisposable.add(
            dataManager.insertArticles(articles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //TODO
                }, {
                    state.value = State.Error(IOException(it))
                    Timber.e(it)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}