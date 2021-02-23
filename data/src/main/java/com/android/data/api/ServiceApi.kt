package com.android.data.api

import com.android.data.response.JokeListResponse
import com.android.data.response.JokeResponse
import retrofit2.Response
import javax.inject.Inject

class ServiceApi @Inject constructor(
    private val serviceEndPoint: ServiceEndPoint
) {

    suspend fun getJokeCategories(): Response<List<String>> {
        return serviceEndPoint.getJokeCategories()
    }

    suspend fun getJokesByQuery(query: String): Response<JokeListResponse> {
        return serviceEndPoint.getJokesByQuery(query)
    }

    suspend fun getRandomJokeByCategory(category: String): Response<JokeResponse> {
        return serviceEndPoint.getRandomJokeByCategory(category)
    }

}