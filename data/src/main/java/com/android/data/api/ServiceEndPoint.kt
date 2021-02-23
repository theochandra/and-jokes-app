package com.android.data.api

import com.android.data.response.JokeListResponse
import com.android.data.response.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceEndPoint {

    @GET("categories")
    suspend fun getJokeCategories(): Response<List<String>>

    @GET("search")
    suspend fun getJokesByQuery(
        @Query("query") query: String
    ): Response<JokeListResponse>

    @GET("random")
    suspend fun getRandomJokeByCategory(
        @Query("category") category: String
    ): Response<JokeResponse>

}