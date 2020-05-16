package com.vicky7230.okcredit_problem.data.network


import com.google.gson.JsonElement
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getNews(section: String): Observable<JsonElement> {
        return apiService.getNews(section)
    }

}