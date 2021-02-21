package com.android.domain.repository

import com.android.domain.Result
import com.android.domain.model.Joke
import com.android.domain.model.JokeList

interface JokeRepository {

    suspend fun getJokeCategories(): Result<List<String>>

    suspend fun getRandomJokeByCategory(category: String): Result<Joke>

    suspend fun getJokesByQuery(query: String): Result<JokeList>

}