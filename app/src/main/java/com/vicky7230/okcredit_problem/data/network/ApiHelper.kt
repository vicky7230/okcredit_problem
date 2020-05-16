package com.vicky7230.okcredit_problem.data.network

import com.google.gson.JsonElement
import io.reactivex.Observable


interface ApiHelper {

    fun getNews(
        section: String
    ): Observable<JsonElement>
}