package com.vicky7230.okcredit_problem.ui.news

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
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
                .subscribe({ data: JsonElement ->
                    if (data.asJsonObject.has("status") && data.asJsonObject["status"].asString == "OK") {

                        val results = data.asJsonObject["results"].asJsonArray

                        val articles = arrayListOf<Article>()
                        results.forEach { result: JsonElement ->
                            var coverImageUrl = ""
                            var thumbnailImageUrl = ""
                            if (result.asJsonObject["multimedia"].isJsonArray) {
                                result.asJsonObject["multimedia"].asJsonArray.forEach {
                                    if (it.asJsonObject["format"].asString == "Standard Thumbnail")
                                        thumbnailImageUrl = it.asJsonObject["url"].asString
                                    if (it.asJsonObject["format"].asString == "mediumThreeByTwo210")
                                        coverImageUrl = it.asJsonObject["url"].asString
                                }
                                result.asJsonObject["multimedia"].asJsonArray[0].asJsonObject["url"].asString
                            }

                            articles.add(
                                Article(
                                    0,
                                    source,
                                    result.asJsonObject["title"].asString,
                                    result.asJsonObject["abstract"].asString,
                                    result.asJsonObject["byline"].asString,
                                    result.asJsonObject["published_date"].asString,
                                    result.asJsonObject["url"].asString,
                                    coverImageUrl,
                                    thumbnailImageUrl
                                )
                            )
                        }

                        insetArticlesInDb(articles)
                    } else {
                        state.value = State.Error(IOException("Something went wrong.. Please try again."))
                    }
                }, {
                    state.value = State.Error(IOException(it))
                    Timber.e(it)
                })

        )
    }

    private fun insetArticlesInDb(articles: ArrayList<Article>) {
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

    fun deleteArticlesFromDb(source: String) {
        state.value = State.Loading

        compositeDisposable.add(
            dataManager.deleteArticles(source)
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