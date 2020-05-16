package com.vicky7230.okcredit_problem.data.network

import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{section}.json")
    fun getNews(
        @Path("section") section: String,
        @Query("api-key") apiKey: String = "xI4AA4gcMj9JyFlyQn2dSAj689PGjKjA"
    ): Observable<JsonElement>
}