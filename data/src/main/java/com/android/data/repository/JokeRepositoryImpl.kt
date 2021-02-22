package com.android.data.repository

import com.android.data.api.ServiceApi
import com.android.data.mapper.JokeMapper
import com.android.data.safeApiCall
import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.model.JokeList
import com.android.domain.repository.JokeRepository

class JokeRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val mapper: JokeMapper
) : JokeRepository {

    override suspend fun getJokeCategories(): Result<List<String>> {
        return safeApiCall(
            call = { getJokeCategoriesFromApi() },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getJokeCategoriesFromApi(): Result<List<String>> {
        val result = serviceApi.getJokeCategories()
        if (result.isSuccessful) {
            val body = result.body()
            body?.let {
                return  Result.Success(it)
            }
        }
        return Result.Error(result.code(), result.message())
    }

    override suspend fun getRandomJokeByCategory(category: String): Result<Joke> {
        return safeApiCall(
            call = { getRandomJokeByCategoryFromApi(category) },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getRandomJokeByCategoryFromApi(category: String): Result<Joke> {
        val result = serviceApi.getRandomJokeByCategory(category)
        if (result.isSuccessful) {
            val body = result.body()
            body?.let {
                val joke = mapper.map(it)
                return Result.Success(joke)
            }
        }
        return Result.Error(result.code(), result.message())
    }

    override suspend fun getJokesByQuery(query: String): Result<JokeList> {
        return safeApiCall(
            call = { getJokesByQueryFromApi(query) },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getJokesByQueryFromApi(query: String): Result<JokeList> {
        val result = serviceApi.getJokesByQuery(query)
        if (result.isSuccessful) {
            val body = result.body()
            body?.let {
                val jokeList = mapper.map(it)
                return Result.Success(jokeList)
            }
        }
        return Result.Error(result.code(), result.message())
    }

}